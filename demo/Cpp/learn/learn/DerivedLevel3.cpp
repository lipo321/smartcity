#include "StdAfx.h"
#include "DerivedLevel3.h"
#include <iostream>
using namespace std;

DerivedLevel3::DerivedLevel3(void)
{
}


DerivedLevel3::~DerivedLevel3(void)
{
}

void DerivedLevel3::f(char* s /* ="unknown" */ )
{
    cout<<"Function f() in DerivedLevel3 called from" <<s <<endl;
    //g("DerivedLevel3");
    DerivedLevel1::h("DerivedLevel3");
    BaseClass::f("DerivedLevel3");
}
