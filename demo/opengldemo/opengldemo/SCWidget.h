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
    //旋转三角形，变量
    GLfloat  m_rTri;
    //旋转四边形，变量
    GLfloat  m_rQuad;


};

#endif