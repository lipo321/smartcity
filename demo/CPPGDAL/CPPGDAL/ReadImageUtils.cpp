#include "ReadImageUtils.h"



int ReadImageUtils::resampleGDAL(const char* pszSrcFile, const char* pszOutFile,
	float fResX, float fResY, GDALResampleAlg eResample)
{
	//注册现有所有的数据驱动
	GDALAllRegister();

	//设置读取文件名称支持的格式是UTF8
	CPLSetConfigOption("GDAL_FILENAME_IS_UTF8", "NO");

	//以只读的方式打开文件；如果打开文件失败，返回。
	GDALDataset *pDSrc = (GDALDataset*)GDALOpen(pszSrcFile, GA_ReadOnly);
	if (NULL == pDSrc)
	{
		return -1;
	}

	
	//获取影像的宽度和高度
	int width  = pDSrc->GetRasterXSize();
	int height = pDSrc->GetRasterYSize();
	      
	//获取影像的波段数，即图像中每个像素点所含的颜色种类，
	//物理中的光学中学过颜色就是某频率的光波。
	//波段少则一个，多则很多个，在遥感影象中波段通常有多个。
	int nBandCount = pDSrc->GetRasterCount();

	//？？？？
	GDALDataType dataType = pDSrc->GetRasterBand(1)->GetRasterDataType();

	//获取影像的投影信息
	char* pszSrcWKT = NULL;
	pszSrcWKT = const_cast<char*>(pDSrc->GetProjectionRef());

	//如果没有投影，设置一个WGS84
	if (0 >= strlen(pszSrcWKT))
	{
		OGRSpatialReference oSRS;
		oSRS.SetUTM(50, true);//北半球 东经120度
		oSRS.SetWellKnownGeogCS("WGS84");
		oSRS.exportToWkt(&pszSrcWKT);
	}

	//在数据驱动管理器中获取数据驱动，如果没有则返回
	GDALDriver* pDriver = GetGDALDriverManager()->GetDriverByName("GTiff");
	if (NULL == pDriver)
	{
		GDALClose((GDALDatasetH)pDSrc);
		return -2;
	}

	//对原始影像进行重投影变换
	void* hTransformArg;
	hTransformArg = GDALCreateGenImgProjTransformer(
		(GDALDatasetH)pDSrc, pszSrcWKT, NULL, pszSrcWKT, FALSE, 0.0, 1);

	//没有投影的影像到这里就走不通了
	if (NULL == hTransformArg)
	{
		GDALClose((GDALDatasetH)pDSrc);
	}

	double dGeoTrans[6] = { 0 };
	int nNewWidth = 0;
	int nNewHeight = 0;

	if (GDALSuggestedWarpOutput((GDALDatasetH)pDSrc, GDALGenImgProjTransform, hTransformArg, dGeoTrans, &nNewWidth, &nNewHeight) != CE_None)
	{
		GDALClose((GDALDatasetH)pDSrc);
		return -3;
	}

	GDALDestroyGenImgProjTransformer(hTransformArg);

	//adfGeoTransform[0] /* top left x */
	//adfGeoTransform[1] /* w-e pixel resolution */
	//adfGeoTransform[2] /* rotation, 0 if image is "north up" */
	//adfGeoTransform[3] /* top left y */
	//adfGeoTransform[4] /* rotation, 0 if image is "north up" */
	//adfGeoTransform[5] /* n-s pixel resolution */

	dGeoTrans[1] = dGeoTrans[1] / fResX;
	dGeoTrans[5] = dGeoTrans[5] / fResY;
	nNewWidth = static_cast<int>(nNewWidth*fResX + 0.5);
	nNewHeight = static_cast<int>(nNewHeight*fResY + 0.5);

	//创建结果数据集
	GDALDataset *pDDst = pDriver->Create(pszOutFile, nNewWidth, nNewHeight, nBandCount, dataType, NULL);
	if (pDDst == NULL)
	{
		GDALClose((GDALDatasetH)pDSrc);
		return -2;
	}

	pDDst->SetProjection(pszSrcWKT);
	pDDst->SetGeoTransform(dGeoTrans);

	GDALWarpOptions *psWo = GDALCreateWarpOptions();

	//psWo->papszWarpOptions = CSLDuplicate(NULL);  
	psWo->eWorkingDataType = dataType;
	psWo->eResampleAlg = eResample;

	psWo->hSrcDS = (GDALDatasetH)pDSrc;
	psWo->hDstDS = (GDALDatasetH)pDDst;

	psWo->pfnTransformer = GDALGenImgProjTransform;
	psWo->pTransformerArg = GDALCreateGenImgProjTransformer((GDALDatasetH)pDSrc, pszSrcWKT, (GDALDatasetH)pDDst, pszSrcWKT, FALSE, 0.0, 1);;

	psWo->nBandCount = nBandCount;
	psWo->panSrcBands = (int *)CPLMalloc(nBandCount * sizeof(int));
	psWo->panDstBands = (int *)CPLMalloc(nBandCount * sizeof(int));
	for (int i = 0; i<nBandCount; i++)
	{
		psWo->panSrcBands[i] = i + 1;
		psWo->panDstBands[i] = i + 1;
	}

	GDALWarpOperation oWo;
	if (oWo.Initialize(psWo) != CE_None)
	{
		GDALClose((GDALDatasetH)pDSrc);
		GDALClose((GDALDatasetH)pDDst);
		return -3;
	}

	oWo.ChunkAndWarpImage(0, 0, nNewWidth, nNewHeight);
	GDALFlushCache(pDDst);

	GDALDestroyGenImgProjTransformer(psWo->pTransformerArg);
	GDALDestroyWarpOptions(psWo);
	GDALClose((GDALDatasetH)pDSrc);
	GDALClose((GDALDatasetH)pDDst);

	return 0;


}