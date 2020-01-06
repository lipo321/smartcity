#include "controlavatar.h"

ControlAvatar::ControlAvatar(QObject *parent)
	: QObject(parent)
{

}

ControlAvatar::~ControlAvatar()
{

}

bool ControlAvatar::handle(const osgGA::GUIEventAdapter& ea, osgGA::GUIActionAdapter& aa)
{
	switch (ea.getEventType())
	{
		case osgGA::GUIEventAdapter::KEYUP:
			int i = ea.getKey();
			if (ea.getKey() == osgGA::GUIEventAdapter::KEY_Up
				|| ea.getKey() == osgGA::GUIEventAdapter::KEY_W)
			{
				// ��
				emit signalUp();
				return true;
			}
			else if (ea.getKey() == osgGA::GUIEventAdapter::KEY_Down
				|| ea.getKey() == osgGA::GUIEventAdapter::KEY_S)
			{
				// ��
				emit signalDown();
				return true;
			}
			else if (ea.getKey() == osgGA::GUIEventAdapter::KEY_Left
				|| ea.getKey() == osgGA::GUIEventAdapter::KEY_A)
			{
				// ��
				emit signalLeft();
				return true;
			}
			else if (ea.getKey() == osgGA::GUIEventAdapter::KEY_Right
				|| ea.getKey() == osgGA::GUIEventAdapter::KEY_D)
			{
				// ��
				emit signalRight();
				return true;
			}
			break;
	}
	return false;
}
