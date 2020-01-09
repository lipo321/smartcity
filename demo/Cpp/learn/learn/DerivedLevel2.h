#pragma once
#include "baseclass.h"
class DerivedLevel2 :
    public BaseClass
{
public:
    DerivedLevel2(void);
    virtual ~DerivedLevel2(void);

    void f(char* s ="unknown" );
};

