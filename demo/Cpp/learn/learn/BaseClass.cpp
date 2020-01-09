#include "StdAfx.h"
#include "BaseClass.h"
#include <iostream>

using namespace std;
BaseClass::BaseClass(void)
{
}


BaseClass::~BaseClass(void)
{
}

void BaseClass::f(char* s/* ="unknown" */)
{
    cout<<"Function f() in BaseClass called from"<<s<<endl;
    h();
}

void BaseClass::g(char* s/* ="unknown" */)
{
    cout<<"Function g() in BaseClass called from"<<s<<endl;
}

void BaseClass::h()
{
    cout<<"Function h() in BaseClass\n";
}

