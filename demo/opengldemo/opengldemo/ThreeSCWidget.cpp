#include "ThreeSCWidget.h"


ThreeSCWidget::ThreeSCWidget(QWidget* parent,const char* name,bool isFullScreen)
    :SCWidget( parent,name, isFullScreen)
{
}


ThreeSCWidget::~ThreeSCWidget(void)
{
}


void ThreeSCWidget::paintGL()
{
    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();
    glTranslatef(-1.5,0.0,-6.0);

    glRotatef(m_rTri,0.0,1.0,0.0);

    glBegin(GL_TRIANGLES);
    glColor3f( 1.0, 0.0, 0.0 );
    glVertex3f(  0.0,  1.0,  0.0 );
    glColor3f( 0.0, 1.0, 0.0 );
    glVertex3f( -1.0, -1.0,  1.0 );
    glColor3f( 0.0, 0.0, 1.0 );
    glVertex3f(  1.0, -1.0,  1.0 );
    glColor3f( 1.0, 0.0, 0.0 );
    glVertex3f(  0.0,  1.0,  0.0 );
    glColor3f( 0.0, 0.0, 1.0 );
    glVertex3f(  1.0, -1.0,  1.0 );
    glColor3f( 0.0, 1.0, 0.0 );
    glVertex3f(  1.0, -1.0, -1.0 );
    glColor3f( 1.0, 0.0, 0.0 );
    glVertex3f(  0.0,  1.0,  0.0 );
    glColor3f( 0.0, 1.0, 0.0 );
    glVertex3f(  1.0, -1.0, -1.0 );
    glColor3f( 0.0, 0.0, 1.0 );
    glVertex3f( -1.0, -1.0, -1.0 );
    glColor3f( 1.0, 0.0, 0.0 );
    glVertex3f(  0.0,  1.0,  0.0 );
    glColor3f( 0.0, 0.0, 1.0 );
    glVertex3f( -1.0, -1.0, -1.0 );
    glColor3f( 0.0, 1.0, 0.0 );
    glVertex3f( -1.0, -1.0,  1.0 );
    glEnd();

    glLoadIdentity();
    glTranslatef(  1.5,  0.0, -7.0 );
    glRotatef( m_rQuad,  1.0,  1.0,  1.0 );

    glBegin( GL_QUADS );
    glColor3f( 0.0, 1.0, 0.0 );
    glVertex3f(  1.0,  1.0, -1.0 );
    glVertex3f( -1.0,  1.0, -1.0 );
    glVertex3f( -1.0,  1.0,  1.0 );
    glVertex3f(  1.0,  1.0,  1.0 );
    glColor3f( 1.0, 0.5, 0.0 );
    glVertex3f(  1.0, -1.0,  1.0 );
    glVertex3f( -1.0, -1.0,  1.0 );
    glVertex3f( -1.0, -1.0, -1.0 );
    glVertex3f(  1.0, -1.0, -1.0 );
    glColor3f( 1.0, 0.0, 0.0 );
    glVertex3f(  1.0,  1.0,  1.0 );
    glVertex3f( -1.0,  1.0,  1.0 );
    glVertex3f( -1.0, -1.0,  1.0 );
    glVertex3f(  1.0, -1.0,  1.0 );
    glColor3f( 1.0, 1.0, 0.0 );
    glVertex3f(  1.0, -1.0, -1.0 );
    glVertex3f( -1.0, -1.0, -1.0 );
    glVertex3f( -1.0,  1.0, -1.0 );
    glVertex3f(  1.0,  1.0, -1.0 );
    glColor3f( 0.0, 0.0, 1.0 );
    glVertex3f( -1.0,  1.0,  1.0 );
    glVertex3f( -1.0,  1.0, -1.0 );
    glVertex3f( -1.0, -1.0, -1.0 );
    glVertex3f( -1.0, -1.0,  1.0 );

    glColor3f( 1.0, 0.0, 1.0 );
    glVertex3f(  1.0,  1.0, -1.0 );
    glVertex3f(  1.0,  1.0,  1.0 );
    glVertex3f(  1.0, -1.0,  1.0 );
    glVertex3f(  1.0, -1.0, -1.0 );
    glEnd();

    m_rTri += 20;
    m_rQuad -= 15;

}
