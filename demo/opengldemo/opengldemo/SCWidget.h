#ifndef SC_WIDGET_H
#define SC_WIDGET_H

#include <qgl.h>
class SCWidget :public QGLWidget
{
    Q_OBJECT
public:

    SCWidget(QWidget* parent=0,const char* name=0,bool isFullScreen = false);
    virtual ~SCWidget(void);

public:
    virtual void paintGL();
protected:
    void initializeGL();
    
    void resizeGL(int width,int height);
    void keyPressEvent(QKeyEvent * e);

protected:
    bool isFullScreen;
    //��ת�����Σ�����
    GLfloat  m_rTri;
    //��ת�ı��Σ�����
    GLfloat  m_rQuad;


};

#endif