// threadDemo.cpp : �������̨Ӧ�ó������ڵ㡣
//

#include "stdafx.h"
#include <iostream>
#include <thread>
#include <future>

#include "realwork.h"
#include "Counter.h"
using namespace std;;



void helloworld()
{
	cout << "Thread World! Hello\n";
}

double caculate(int v)
{
	if (v<=0)
	{
		return v;
	}

	//��������������
	this_thread::sleep_for(chrono::microseconds(10));
	return sqrt((v * v + sqrt((v - 5) * (v + 2.5)) / 2.0) / v);
}

template<typename Iter,typename Fun>
double visitRange(thread::id id, Iter iterBegin, Iter IterEnd, Fun func)
{
	auto curId = this_thread::get_id();
	if (id == curId)
	{
		cout << curId << "hello main thread\n";
	}
	else {
		cout << curId << "hello work thread\n";
	}
	double v = 0;
	for (auto iter = iterBegin; iter != IterEnd;++iter) {
		v += func(*iter);
	}
	return v;
}

int main()
{
#if 0
	//����һ���߳�
	thread t(helloworld);
	cout << "hello world main thread\n";

	//�̵߳��ս�
	t.join();
///////////////////////////////////////////
	//��ȡ���̵߳�id
	auto mainThreadId = this_thread::get_id();

	vector<double> v;
	for (int i = 0; i < 1000; i++) {
		v.push_back(rand());
	}

	cout << v.size() << endl;
	double value = 0.0;
	auto startTime = clock();
	for (auto& info:v)
	{
		value += caculate(info);
	}
	auto endTime = clock();
	cout << "single thread:" << value << " "
		<< endTime - startTime << "time" << endl;


	//�����ö��߳�������
	auto iterMid = v.begin() + (v.size() / 2);

	//�����벿��
	double anotherv = 0.0;
	auto iterEnd = v.end();
	startTime = clock();
	//���߳�
	thread s([&anotherv, mainThreadId, iterMid, iterEnd]() {
		anotherv = visitRange(mainThreadId, iterMid, iterEnd, caculate);
	});


	// ����ǰ�벿�� 
	auto halfv = visitRange(mainThreadId, v.begin(), iterMid, caculate);
	//�ر��߳� 
	s.join();

	endTime = clock();
	cout << "multi thread: " << (halfv + anotherv) << " " << endTime - startTime << "time" << endl;

#else
	//��ȡӲ��֧�ֵĲ����߳���
	unsigned n = thread::hardware_concurrency();
	cout << n << "  concurrent threads are support.\n";

	vector<int> vec;
	double totalValue = 0;
	for (int i = 0; i < 1000000; i++)
	{
		vec.push_back(rand() % 100);
	}
	Counter counter;
	realWork(counter, totalValue, vec.begin(), vec.end());
	cout << "tatal times:" << counter.count() << " "
		<< totalValue << endl;

	//���߳�
	totalValue = 0;
	Counter counter2;
	auto iter = vec.begin() + (vec.size() / 3);
	auto iter2 = vec.begin() + (vec.size() / 3 * 2);
	thread b([&counter2, &totalValue, iter, iter2]() {
		realWork(counter2, totalValue, iter, iter2);
	});

	double totalValueC = 0;
	auto end = vec.end();
	thread c([&counter2, &totalValueC, iter2, end]() {
		realWork(counter2, totalValueC, iter2, end);
	});


	double totalValueD = 0;
	realWork(counter2, totalValueD, vec.begin(), iter);
	b.join();
	c.join();
	cout << "total times use multithread: " << counter2.count() << " "
		<< totalValue+totalValueC+totalValueD << endl;
	
#endif
	
    return 0;
}

