#include <QtCore/QCoreApplication>

#include"ReadImageUtils.h"

int main(int argc, char *argv[])
{
	QCoreApplication a(argc, argv);
	
	ReadImageUtils::resampleGDAL("C:\\Data\\test71.tiff",
		"C:\\Data\\resample_test71.tiff",10,10);

	return a.exec();
}
