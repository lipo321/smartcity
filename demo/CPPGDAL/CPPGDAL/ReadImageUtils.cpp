#include "ReadImageUtils.h"



int ReadImageUtils::resampleGDAL(const char* pszSrcFile, const char* pszOutFile,
	float fResX, float fResY, GDALResampleAlg eResample)
{
	//ע���������е���������
	GDALAllRegister();

	//���ö�ȡ�ļ�����֧�ֵĸ�ʽ��UTF8
	CPLSetConfigOption("GDAL_FILENAME_IS_UTF8", "NO");

	//��ֻ���ķ�ʽ���ļ���������ļ�ʧ�ܣ����ء�
	GDALDataset *pDSrc = (GDALDataset*)GDALOpen(pszSrcFile, GA_ReadOnly);
	if (NULL == pDSrc)
	{
		return -1;
	}

	
	//��ȡӰ��Ŀ�Ⱥ͸߶�
	int width  = pDSrc->GetRasterXSize();
	int height = pDSrc->GetRasterYSize();
	      
	//��ȡӰ��Ĳ���������ͼ����ÿ�����ص���������ɫ���࣬
	//�����еĹ�ѧ��ѧ����ɫ����ĳƵ�ʵĹⲨ��
	//��������һ��������ܶ������ң��Ӱ���в���ͨ���ж����
	int nBandCount = pDSrc->GetRasterCount();

	//��������
	GDALDataType dataType = pDSrc->GetRasterBand(1)->GetRasterDataType();

	//��ȡӰ���ͶӰ��Ϣ
	char* pszSrcWKT = NULL;
	pszSrcWKT = const_cast<char*>(pDSrc->GetProjectionRef());

	//���û��ͶӰ������һ��WGS84
	if (0 >= strlen(pszSrcWKT))
	{
		OGRSpatialReference oSRS;
		oSRS.SetUTM(50, true);//������ ����120��
		oSRS.SetWellKnownGeogCS("WGS84");
		oSRS.exportToWkt(&pszSrcWKT);
	}

	//�����������������л�ȡ�������������û���򷵻�
	GDALDriver* pDriver = GetGDALDriverManager()->GetDriverByName("GTiff");
	if (NULL == pDriver)
	{
		GDALClose((GDALDatasetH)pDSrc);
		return -2;
	}

	//��ԭʼӰ�������ͶӰ�任
	void* hTransformArg;
	hTransformArg = GDALCreateGenImgProjTransformer(
		(GDALDatasetH)pDSrc, pszSrcWKT, NULL, pszSrcWKT, FALSE, 0.0, 1);

	//û��ͶӰ��Ӱ��������߲�ͨ��
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

	//����������ݼ�
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