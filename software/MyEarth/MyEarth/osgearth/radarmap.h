#ifndef RADARMAP_H
#define RADARMAP_H

#include "framehandle.h"
#include "graphicsview.h"

class RadarMap : public QObject
{
	Q_OBJECT

public:
	RadarMap(GraphicsView* view, QObject *parent = nullptr);
	~RadarMap();

	void setGeomtry(int x = 20, int y = 20, int width = 320, int height = 180)
	{
		m_iX = x; m_iY = y; m_iWidth = width; m_iHeight = height;
	}

	void enableMap();
	void disableMap();

private slots:
	void slotFrameViewport(const osg::Vec3d& pos);

private:
	void createMap();
	// ������������ȥ��γ������
	osg::Vec3d getLonLat(const osg::Vec3d& worldPos);

private:
	// ��ʾ������
	int m_iX;
	int m_iY;
	int m_iWidth;
	int m_iHeight;

	// HUD���
	osg::ref_ptr<osg::Camera> m_pHUDCamera;
	GraphicsView* m_pOSGViewer;
	osg::ref_ptr<osg::Group> m_pGroup;
	FrameHandle* m_pFrameHandle;

	// ʮ�ּܣ���ʾ��ǰ�ӵ�λ��
	osg::ref_ptr<osg::Geode> m_pGeodeCross;
	osg::ref_ptr<osg::Vec3dArray> m_pVertexCross;
};

#endif // RADARMAP_H
