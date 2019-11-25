#ifndef READ_IMAGE_UTILS
#define READ_IMAGE_UTILS

#include"gdal_priv.h"
#include"ogrsf_frmts.h"
#include"gdalwarper.h"



class ReadImageUtils
{
public:
	/*
	*遥感影像重采样（要求影像必须有投影,否则走不通）
	*@param pszSrcFile 输入文件的路径
	*@param pszOutFile 写入的结果图像的路径
	* @param eResample		   采样模式，有五种，具体参见GDALResampleAlg定义，默认为双线性内插 
	* @param fResX             X转换采样比，默认大小为1.0，大于1图像变大，小于1表示图像缩小。数值上等于采样后图像的宽度和采样前图像宽度的比 
	* @param fResY             Y转换采样比，默认大小为1.0，大于1图像变大，小于1表示图像缩小。数值上等于采样后图像的高度和采样前图像高度的比
	* @retrieve		0	成功
	* @retrieve		-1	打开源文件失败
	* @retrieve		-2	创建新文件失败
	* @retrieve		-3	处理过程中出错
	*/
	static int resampleGDAL(const char* pszSrcFile, const char* pszOutFile,
		float fResX = 1.0, float fResY = 1.0, GDALResampleAlg eResample = GRA_Bilinear);


};

#endif // !READ_IMAGE_UTILS