#ifndef MYEARTH_H
#define MYEARTH_H

#include <QtGui/QMainWindow>
#include "ui_myearth.h"

class MyEarth : public QMainWindow
{
	Q_OBJECT

public:
	MyEarth(QWidget *parent = 0, Qt::WFlags flags = 0);
	~MyEarth();

private:
	Ui::MyEarthClass ui;
};

#endif // MYEARTH_H
