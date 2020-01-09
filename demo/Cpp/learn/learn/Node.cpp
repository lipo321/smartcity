#include "StdAfx.h"
#include "Node.h"
#include <string.h>
#include <stdlib.h>

Node::Node(void)
{
}

Node::Node(char* n/* =" " */,int a /* =0 */){
    //strdup����������c�����г��õ�һ���ַ��������⺯����һ���free���������ɶԳ���
    name = _strdup(n);
    age  = a;
}

Node::Node(const Node& n)
{
    name = _strdup(n.name);
    age  = n.age;
}

Node& Node::operator=(const Node& n){
    if (this != &n)
    {
        if (name != 0)
        {
            free(name);
        }

        name  =_strdup(n.name);
        age = n.age;
    }
    return *this;
}

Node::~Node(void)
{
    if (name != nullptr)
    {
        free(name);
    }
}
