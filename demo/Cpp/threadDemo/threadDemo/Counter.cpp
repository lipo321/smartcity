#include "stdafx.h"
#include "Counter.h"
#include <atomic>

Counter::Counter()
{
	m_count = 0;
	m_totalResource = 0;
}


Counter::~Counter()
{
}

void Counter::addCount()
{
	
	m_count++;
}

int Counter::aveResource()
{
	m_mutex.lock();
	if (m_count == 0)
	{
		m_mutex.unlock();
		return 1;
	}
	auto r = m_totalResource / m_count;
	m_mutex.unlock();
	return r;
}

void Counter::addCountAndResource(int r)
{
	m_mutex.lock();
	addCount();
	addResource(r);
	m_mutex.unlock();
}

void Counter::addResource(int a)
{
	m_totalResource++;
}
int Counter::count() 
{
	m_mutex.lock();
	auto r =m_count;
	m_mutex.unlock();
	return r;
}