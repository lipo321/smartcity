/* --------------------------------------------
** ��Ȩ: 
** ����: math
** ����: lyk 
** ����: 2015/06/23 
** ����: 
** ------------------------------------------*/
#ifndef MATH_H
#define MATH_H

#include "MyOSG.h"

class CalcMath
{
public:
	CalcMath();
	~CalcMath();

	// ����������㹫ʽ
	double calcArea(std::vector<osg::Vec3d> points);

private:
	
};

#endif // MATH_H
