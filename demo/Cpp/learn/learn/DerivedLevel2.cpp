#include "StdAfx.h"
#include "DerivedLevel2.h"
#include <iostream>

using namespace std;
DerivedLevel2::DerivedLevel2(void)
{
}


DerivedLevel2::~DerivedLevel2(void)
{
}


void DerivedLevel2::f(char* s/* ="unknown" */)
{
    cout<<"Function f() in DerivedLevel2 called from"<<s<<endl;
    g("DerivedLevel2");
}

