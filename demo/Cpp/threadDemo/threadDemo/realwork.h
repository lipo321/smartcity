#pragma once
#include "Counter.h"
#include <iostream>

using namespace std;

int work(int a) {
	return a * 2;
}

template<typename Iter>
void realWork(Counter& c, double& totalValue, Iter b, Iter e)
{
	for (; b != e; ++b) {
		totalValue += work(*b);
		c.addCountAndResource(1);
	}
}


bool average(Counter& c, int maxCount)
{
	auto cnt = c.count();
	auto ave = c.aveResource();
	if (ave != 1) cout << "has bad thing happened\n";
	if (cnt == maxCount) {
		cout << " ok finished \n";
		return true;
	}
	return false;
}
