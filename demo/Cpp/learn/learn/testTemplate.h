#ifndef TESTTEMPLATE_H
#define TESTTEMPLATE_H

#include <iostream>
#include <vector>
#include <algorithm>
#include <functional>


using namespace std;

template<class T>
void printVector(char* s,const vector<T>& v){
    cout<<s<<"=(";
    if (v.size() == 0)
    {
        cout<<")\n";
        return;
    }
    typename vector<T>::const_iterator i = v.begin();
    for(; i!= v.end()-1;i++){
        cout<<*i<<' ';
    }
    cout<<*i<<")\n";
}

bool f1(int n){
    return n<4;
}

#endif