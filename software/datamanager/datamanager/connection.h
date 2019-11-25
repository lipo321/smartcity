#ifndef CONNECTION_H
#define CONNECTION_H

#include <QtSql/QtSql>
#include <QDebug>
#include <QtXml/QtXml>

//���ݹ�������
class manageUtils
{
public:
    static bool createConnection()
    {
        QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE");
        qDebug() << "drivers" << QSqlDatabase::drivers();
        
        db.setHostName("lipoWindow");
        db.setDatabaseName("data.db");
        db.setUserName("lipo");
        db.setPassword("123456");

        if (!db.open())
        {
            qDebug()<<"���ݿ������������û���������"<<endl;
            return false;
        }


        QSqlQuery query;

        //1.�������η����
        query.exec("create table type(id int32 primary key,name varchar)");
        query.exec(QString("insert into type values(1,'��������')"));
        query.exec(QString("insert into type values(2,'shape(.shp)')"));
        query.exec(QString("insert into type values(3,'openstreetmap(.osm)')"));

        //2.��������Ԫ��Ϣ��
        query.exec("create table metadata(id int32 primary key,name varchar, type int32, des varchar, geom_wkt varcahr)");
        query.exec(QString("insert into metadata values(1,'֣��',2,'·������','bbox(1,2,2,3)')"));
        query.exec(QString("insert into metadata values(2,'����',2,'·������','bbox(1,2,2,3)')"));
        query.exec(QString("insert into metadata values(3,'���',2,'·������','bbox(1,2,2,3)')"));
        query.exec(QString("insert into metadata values(4,'����',3,'��������','bbox(1,2,2,3)')"));
        query.exec(QString("insert into metadata values(5,'����',3,'�ؿ�����','bbox(1,2,2,3)')"));

        //3.���������
        query.exec("create table user(name varchar primary key,password varchar)");
        query.exec("insert into user values('lipo','123456')");

        return true;

    }


    static bool createXml()
    {
        QFile file("data.xml");
        if(file.exists()) return true;
        if(!file.open(QIODevice::WriteOnly | QIODevice::Truncate)) return false;
        QDomDocument doc;
        QDomProcessingInstruction instruction;
        instruction = doc.createProcessingInstruction("xml","version=\"1.0\" encoding=\"UTF-8\"");
        doc.appendChild(instruction);
        QDomElement root = doc.createElement(QString("���������嵥"));
        doc.appendChild(root);

        QTextStream out(&file);
        doc.save(out,4);
        file.close();
        return true;
    }
};



#endif