// learn.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"

#include "BaseClass.h"
#include "DerivedLevel1.h"
#include "DerivedLevel2.h"
#include "DerivedLevel3.h"

#include "Class1.h"
#include "Class2.h"
#include "Class3.h"

#include <iostream>
using namespace std;

double f(double x)
{
    return 2*x;
}


double sum(double (*f)(double), int n,int m)
{
    double result = 0;
    for (int i = n;i<=m;i++)
    {
        result += f(i);
    }
    return result;
}
//二分法寻找中心点
double root(double (*f)(double),double a,double b,double epsilon)
{
    double middle = (a+b)/2;
    while(f(middle) !=0 && fabs(b-a)>epsilon)
    {
        if (fabs(a)< fabs(f(middle)))
        {
            b =middle;
        }else{
            a =middle;
        }
        middle =(a+b)/2;
    }
    return middle;
}
int _tmain(int argc, _TCHAR* argv[])
{
#if 0
    BaseClass bc;
    DerivedLevel1 d1;
    DerivedLevel2 d2;
    DerivedLevel3 d3;

    bc.f("main(1)");

    d1.f("main(2)");
    d1.h("main(3)");

    d2.f("main(4)");


    d3.f("main(5)");


    cout<< sum(f,1,5)<<endl;
    cout<< sum(sin,3,7)<<endl;

    cout<<root(f,1,9,0.2)<<endl;
#else
    Class1 object1,*p;
    Class2 object2;
    Class3 object3;

    p=&object1;
    p->f();
    p->g();

    p=(Class1*)&object2;
    p->f();
    p->g();

    p=(Class1*)&object3;
    p->g();
#endif
    
	return 0;
}

