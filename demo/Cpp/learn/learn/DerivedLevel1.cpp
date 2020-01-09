#include "StdAfx.h"
#include "DerivedLevel1.h"
#include <iostream>

using namespace std;

DerivedLevel1::DerivedLevel1(void)
{
}


DerivedLevel1::~DerivedLevel1(void)
{
}

void DerivedLevel1::f(char* s /* ="unknown" */ )
{
    cout<<"Function f() in DerivedLevel1 called from"<<s<<endl;
    g("DerivedLevel1");
    h("DerivedLevel1");
}

void DerivedLevel1::h(char* s /* ="unknown" */ )
{
    cout<<"Function h() in DerivedLevel1 called from"<<s<<endl;
}