#ifndef DERIVEDLEVEL3_H
#define DERIVEDLEVEL3_H
#include "DerivedLevel1.h"
#include "DerivedLevel2.h"
#include "BaseClass.h"
class DerivedLevel3 :public DerivedLevel1 ,public DerivedLevel2
{
public:
    DerivedLevel3(void);
    virtual ~DerivedLevel3(void);

public:
    void f(char* s  ="unknown"  );
};
#endif
