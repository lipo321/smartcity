#include "opengldemo.h"

#include <QMessageBox>
#include <QGraphicsView>

#include "ThreeSCWidget.h"
opengldemo::opengldemo(QWidget *parent, Qt::WFlags flags)
    : QMainWindow(parent, flags)
{
    ui.setupUi(this);
    
    createMenu();
    createToolBar();
    
}

opengldemo::~opengldemo()
{

}
void opengldemo::createMenu()
{
    QMenuBar * menuBar = new QMenuBar(this);

    QMenu * menu = new QMenu("File", this);
    QAction * newFile = new QAction(QIcon(), "NewFile", this);//此处QIcon可给Action一个图标
    QAction * openFile = new QAction(QIcon(), "OpenFile", this);
    QAction * save = new QAction(QIcon(), "Save", this);
    openFile->setShortcuts(QKeySequence::Open);//此处为使用快捷键
    save->setShortcut(QKeySequence::Save);//更多详细快捷键内容查看帮助文档
    menu->addAction(newFile);
    menu->addAction(openFile);
    menu->addAction(save);

    menuBar->addMenu(menu);
    this->setMenuBar(menuBar);

    
}

void opengldemo::createToolBar()
{
    QToolBar * toolBar = new QToolBar(this);

    QAction * save = new QAction("open GLWidget", this);
    toolBar->addAction(save);
    connect(save,SIGNAL(triggered()),this,SLOT(openGLWidget()));



    QAction * reload = new QAction("reload", this);
    toolBar->addAction(reload);
    connect(reload,SIGNAL(triggered()),this,SLOT(reload()));

    QAction* ThreeDWigetAction = new QAction("open 3DWidget",this);
    toolBar->addAction(ThreeDWigetAction);
    connect(ThreeDWigetAction,SIGNAL(triggered()),this,SLOT(open3DGLWidget()));

    this->addToolBar(toolBar);
    


}


void opengldemo::openGLWidget()
{

    bool isFullSceen = false;

    switch( QMessageBox::information(0,"Start FullScreen?","Would you like To Run with FullScreen?",
        QMessageBox::Yes,QMessageBox::No|QMessageBox::Default)){
    case QMessageBox::Yes:
        isFullSceen = true;
        break;
    case QMessageBox::No:
        isFullSceen = false;
        break;
    }

    p_SCWidget = new SCWidget(this,"first opengl widget",isFullSceen);
    setCentralWidget(p_SCWidget);
}

void opengldemo::open3DGLWidget()
{
    p_SCWidget = new ThreeSCWidget(this,"first opengl widget",false);
    setCentralWidget(p_SCWidget);
}

void opengldemo::reload()
{
    this->centralWidget()->repaint();
}

