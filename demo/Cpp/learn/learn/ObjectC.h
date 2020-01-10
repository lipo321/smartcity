#ifndef OBJECTC_H
#define OBJECTC_H

class ObjectC
{
public:
    ObjectC(void);
    virtual ~ObjectC(void);
private:
    int n;
    friend int f();
}ob;
#endif
