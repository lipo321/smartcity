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
#include "classF.h"

#include <iostream>
#include <vector>

#include "testTemplate.h"

#include "Person.h"
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

//
double sum2(classF f,int n,int m){
    double result = 0;
    for(int i = n;i<=m;i++){
        result += f(i);
    }
    return result;
}

template<class F>
double sum3(F f,int n,int m)
{
    double result = 0;
    for(int i =n;i<m;i++){
        result += f(i);
    }
    return result;
}

//template<class T>
//struct negate:public unary_function<T,T>{
//    T operator()(const T& x)const{
//        return -x;
//    }
//};
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

    Class1 object1,*p;
    Class2 object2;
    Class3 object3;

    p=&object1;
    p->f();
    p->g();

    p=(Class1*)&object2;
    p->f();
    p->g();

    object3.h();

   classF cf;
    cout<<sum2(cf,2,5)<<endl;
    cout<<sum3(cf,2,5)<<endl;
////////////////////////////////////////////////////
    int a[] = {1,2,3,4,5};
    vector<int> v1;
    printVector("v1",v1);
    for (int j=1;j<=5;j++)
    {
        v1.push_back(j);
    }
    int capacity = v1.capacity();
    
    vector<int> v2(3,7);
    capacity = v2.capacity();


    vector<int>::iterator i1 = v1.begin()+1;
    vector<int> v3(i1,i1+2);

    vector<int> v4(v1);

    vector<int> v5(5);
    v5[1] = v5.at(3) = 9;
    v3.reserve(6);
    v4.resize(7);
    v4.resize(3);
    v4.clear();

    v4.insert(v4.end(),v3[1]);
    v4.insert(v4.end(),v3.at(1));
    v4.insert(v4.end(),2,4);
    v4.insert(v4.end(),v1.begin()+1,v1.end()-1);
    v4.erase(v4.end()-2);
    v4.erase(v4.begin(),v4.begin()+4);
    v4.assign(3,8);
    v4.assign(a,a+3);
    vector<int>::reverse_iterator i3 =v4.rbegin();
    for(;i3 != v4.rend();i3++){
        cout<<*i3<<' ';
    }
    cout<<endl;

    //算法
    v5[0] = 3;
    replace_if(v5.begin(),v5.end(),f1,7);
    v5[0]=3;
    v5[2]=v5[4]=0;
    replace(v5.begin(),v5.end(),0,7);
    sort(v5.begin(),v5.end());
    sort(v5.begin(),v5.end(),greater<int>());
    v5.front() = 2;
    return 0;
#else
    vector<Person> v6;
    v6.push_back(Person("Gregg",25));
    v6.push_back(Person("Ann",30));
    v6.push_back(Person("Bill",30));

    sort(v6.begin(),v6.end());

#endif

	return 0;
}

