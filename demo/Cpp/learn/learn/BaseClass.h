#ifndef BASECLASS_H
#define BASECLASS_H

class BaseClass
{
public:
    BaseClass(void);
    virtual ~BaseClass(void);

    void f(char* s="unknown");
protected:
    void g(char* s="unknown");
private:
    void h();
};
#endif
