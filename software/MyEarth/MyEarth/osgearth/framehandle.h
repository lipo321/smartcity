/* --------------------------------------------
** ��Ȩ: 
** ����: framehandle
** ����: louyk 
** ����: 2015/07/09 
** ����: 
** ------------------------------------------*/
#ifndef FRAMEHANDLE_H
#define FRAMEHANDLE_H

#include <QtCore/QObject>
#include "MyOSG.h"

class FrameHandle : public QObject, public osgGA::GUIEventHandler
{
	Q_OBJECT

public:
	FrameHandle();
	~FrameHandle();

protected:
	virtual bool handle(const osgGA::GUIEventAdapter& ea,
		osgGA::GUIActionAdapter& aa);

signals:
	// ����ÿһ֡ʱ�ӵ�����λ��
	void signalFrameViewport(const osg::Vec3d& pos);

private:
	
};

#endif // FRAMEHANDLE_H
