#include "widget.h"

#include <QtGui/QApplication>
#include <QTextCodec>
#include <QtPlugin>

#include "connection.h"
#include "logindialog.h"


int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    //��Ӳ��
    QCoreApplication::addLibraryPath("D:\\QT\\qt4.8.6_x64\\plugins");

    //1.�����ڴ������ݿ�֮ǰʹ�ã���Ȼ�޷������ݿ���ʹ������
    QTextCodec::setCodecForTr(QTextCodec::codecForLocale());
    QTextCodec::setCodecForCStrings(QTextCodec::codecForLocale());

    //2.��ʼ�����ݿ�
    if (!manageUtils::createConnection() || !manageUtils::createXml())
    {
        qDebug()<<"��ʼ��ʧ��"<<endl;
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
