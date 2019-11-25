#include "opengldemo.h"
#include <QtGui/QApplication>
#include <QMessageBox>


#include "SCWidget.h"
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
 
    opengldemo mainwindow ;
    mainwindow.show();

    return a.exec();
}
