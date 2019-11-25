#ifndef OPENGLDEMO_H
#define OPENGLDEMO_H

#include <QtGui/QMainWindow>
#include "ui_opengldemo.h"
#include "SCWidget.h"
class opengldemo : public QMainWindow
{
    Q_OBJECT

public:
    opengldemo(QWidget *parent = 0, Qt::WFlags flags = 0);
    ~opengldemo();

public slots:
   void openGLWidget();
   void reload();
   void open3DGLWidget();
protected:
   void createMenu();
   void createToolBar();
private:
    Ui::opengldemoClass ui;
    SCWidget*   p_SCWidget;
};

#endif // OPENGLDEMO_H
