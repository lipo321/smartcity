#ifndef NODE_H
#define NODE_H

class Node
{
public:
    Node(void);
    Node(char* n,int a =0);
    Node(const Node& n);

    Node& operator =(const Node& n);
    virtual ~Node(void);

private:
    char* name;
    int age;
};

#endif