#ifndef DERIVEDLEVEL1_H
#define DERIVEDLEVEL1_H

#include "baseclass.h"
class DerivedLevel1 : public BaseClass
{
public:
    DerivedLevel1(void);
    virtual ~DerivedLevel1(void);

    void f(char* s ="unknown" );
    void h(char* s ="unknown" );

};

#endif