#include "OSGEarth.h"
#include <QApplication>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	OSGEarth w;
	w.showMaximized();
	return a.exec();
}
