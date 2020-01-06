#pragma once

#include <QGraphicsView>
#include <QInputEvent>
#include <osgViewer/Viewer>

class OSGEventAdapter :public QGraphicsView
{
public:
	OSGEventAdapter(void);
	virtual ~OSGEventAdapter(void);

protected:
	//�������¼�
	virtual void keyPressEvent(QKeyEvent *event);
	virtual void keyReleaseEvent(QKeyEvent* event);

	//������굥���¼�
	virtual void mousePressEvent(QMouseEvent *event);
	virtual void mouseReleaseEvent(QMouseEvent *event);

	//�������˫���¼�
	virtual void mouseDoubleClickEvent(QMouseEvent *event);
	virtual void mouseMoveEvent(QMouseEvent* event);

	//��������л����¼�
	virtual void wheelEvent(QWheelEvent *event);

	//�����ڴ�С�ı��¼�
	virtual void resizeEvent(QResizeEvent *event);

	//��������ƶ��¼�
	virtual void moveEvent(QMoveEvent *event);

	//timerEvent�¼�
	virtual void timerEvent(QTimerEvent * event);


private:
	void setKeyboardModifiers(QInputEvent* event);

protected:
	osgViewer::GraphicsWindow* m_pGraphicsWindow;
};

