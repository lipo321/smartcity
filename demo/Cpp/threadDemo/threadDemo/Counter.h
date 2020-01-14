#ifndef COUNTER_H
#define COUNTER_H

#include <atomic>
#include <mutex>
class Counter
{
public:
	Counter();
	virtual ~Counter();

	void addCount();
	int count() ;

	int aveResource();
	void addCountAndResource(int r);

private:
	void addResource(int a);
	
private:
	int m_count;
	int m_totalResource;
	std::mutex m_mutex;
};

#endif