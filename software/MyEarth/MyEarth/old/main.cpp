#include "myearth.h"

#include <QtGui/QApplication>
#include <osgViewer/Viewer>
#include <osgGA/TrackballManipulator>
#include <osg/MatrixTransform>
#include <osgManipulator/TabBoxDragger>
#include <osgGA/StateSetManipulator>s
#include <osgViewer/ViewerEventHandlers>
#include <osgDB/ReadFile>
#include <osgEarth/Map>
#include <osgEarth/MapNode>
#include <osgEarthDrivers/gdal/GDALOptions>
#include <osgEarthDrivers/tms/TMSOptions>



int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	MyEarth w;
	w.show();
	return a.exec();
}
