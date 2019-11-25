#ifndef READ_IMAGE_UTILS
#define READ_IMAGE_UTILS

#include"gdal_priv.h"
#include"ogrsf_frmts.h"
#include"gdalwarper.h"



class ReadImageUtils
{
public:
	/*
	*ң��Ӱ���ز�����Ҫ��Ӱ�������ͶӰ,�����߲�ͨ��
	*@param pszSrcFile �����ļ���·��
	*@param pszOutFile д��Ľ��ͼ���·��
	* @param eResample		   ����ģʽ�������֣�����μ�GDALResampleAlg���壬Ĭ��Ϊ˫�����ڲ� 
	* @param fResX             Xת�������ȣ�Ĭ�ϴ�СΪ1.0������1ͼ����С��1��ʾͼ����С����ֵ�ϵ��ڲ�����ͼ��Ŀ�ȺͲ���ǰͼ���ȵı� 
	* @param fResY             Yת�������ȣ�Ĭ�ϴ�СΪ1.0������1ͼ����С��1��ʾͼ����С����ֵ�ϵ��ڲ�����ͼ��ĸ߶ȺͲ���ǰͼ��߶ȵı�
	* @retrieve		0	�ɹ�
	* @retrieve		-1	��Դ�ļ�ʧ��
	* @retrieve		-2	�������ļ�ʧ��
	* @retrieve		-3	��������г���
	*/
	static int resampleGDAL(const char* pszSrcFile, const char* pszOutFile,
		float fResX = 1.0, float fResY = 1.0, GDALResampleAlg eResample = GRA_Bilinear);


};

#endif // !READ_IMAGE_UTILS