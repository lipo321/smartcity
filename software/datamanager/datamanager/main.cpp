#include "widget.h"

#include <QtGui/QApplication>
#include <QTextCodec>
#include <QtPlugin>

#include "connection.h"
#include "logindialog.h"


int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    //添加插件
    QCoreApplication::addLibraryPath("D:\\QT\\qt4.8.6_x64\\plugins");

    //1.必须在创建数据库之前使用，不然无法在数据库中使用中文
    QTextCodec::setCodecForTr(QTextCodec::codecForLocale());
    QTextCodec::setCodecForCStrings(QTextCodec::codecForLocale());

    //2.初始化数据库
    if (!manageUtils::createConnection() || !manageUtils::createXml())
    {
        qDebug()<<"初始化失败"<<endl;
        return 0;
    }

    

    Widget w;
    LoginDialog dlg;
    if (dlg.exec() == QDialog::Accepted)
    {
        w.show();
        return a.exec();
    }
    
    return 0;
}
