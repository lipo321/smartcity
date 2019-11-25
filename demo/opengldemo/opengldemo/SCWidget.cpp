#include "SCWidget.h"

#include <gl/GLU.h>
#include <QKeyEvent>
SCWidget::SCWidget(QWidget* parent,const char* name,bool isFullScreen )
{
    m_rTri =0.2;
    m_rQuad =0.15;
    this->isFullScreen = isFullScreen;
    setGeometry(0,0,640,480);
    setCaption("smartCity openGL Framework");
    if (isFullScreen){
        showFullScreen();
    }
}


SCWidget::~SCWidget(void)
{
}

void SCWidget::initializeGL(){
    glShadeModel(GL_SMOOTH);
    glClearColor(0.0,0.0,0.0,0.0);
    glClearDepth(1.0);
    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LEQUAL);
    glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);

}

void SCWidget::paintGL(){

        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        glTranslatef(-1.5,0.0,-6.0);

        //负责让对象绕着某个旋转轴转动m_rTri
        glRotatef(m_rTri,0.0,1.0,0.0);
        glBegin(GL_TRIANGLES);
        glColor3f( 1.0, 0.0, 0.0);
        glVertex3f(0.0,1.0,0.0);

        glColor3f( 0.0, 1.0, 0.0);
        glVertex3f( -1.0, -1.0,  0.0 );

        glColor3f( 0.0, 0.0, 1.0);
        glVertex3f(  1.0, -1.0,  0.0 );
        glEnd();

        //重置模型观察矩阵
        glLoadIdentity();

        glTranslatef( 1.5,  0.0,  -6.0 );
        glRotatef(m_rQuad,1.0,0.0,0.0);

        glColor3f(0.5,0.5,1.0);
        glBegin( GL_QUADS );
        glVertex3f( -1.0,  1.0,  0.0 ); 
        glVertex3f(  1.0,  1.0,  0.0 ); 
        glVertex3f(  1.0, -1.0,  0.0 );
        glVertex3f( -1.0, -1.0,  0.0 ); 
        glEnd();

        m_rTri +=10;
        m_rQuad -=15;

}

void SCWidget::resizeGL(int width,int height){
    if (height == 0)
    {
        height = 1;
    }
    if (width == 0)
    {
        width = 1;
    }

    glViewport(0,0,(GLint)width,(GLint)height);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(45.0,(GLfloat)width/(GLfloat)height,0.1,100.0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}

void SCWidget::keyPressEvent(QKeyEvent * e){
    switch(e->key()){
    case Qt::Key_F2:
        isFullScreen = !isFullScreen;
        if (isFullScreen)
        {
            showFullScreen();
        }else{
            showNormal();
            setGeometry(0,0,640,480);
        }
        updateGL();
        break;
    case Qt::Key_Escape:
        close();
    }
}