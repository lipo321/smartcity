#ifndef CONNECTION_H
#define CONNECTION_H

#include <QtSql/QtSql>
#include <QDebug>
#include <QtXml/QtXml>

//数据管理工具类
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
            qDebug()<<"数据库密码错误或者用户名不存在"<<endl;
            return false;
        }


        QSqlQuery query;

        //1.创建几何分类表
        query.exec("create table type(id int32 primary key,name varchar)");
        query.exec(QString("insert into type values(1,'数据类型')"));
        query.exec(QString("insert into type values(2,'shape(.shp)')"));
        query.exec(QString("insert into type values(3,'openstreetmap(.osm)')"));

        //2.创建数据元信息表
        query.exec("create table metadata(id int32 primary key,name varchar, type int32, des varchar, geom_wkt varcahr)");
        query.exec(QString("insert into metadata values(1,'郑州',2,'路网数据','bbox(1,2,2,3)')"));
        query.exec(QString("insert into metadata values(2,'安阳',2,'路网数据','bbox(1,2,2,3)')"));
        query.exec(QString("insert into metadata values(3,'濮阳',2,'路网数据','bbox(1,2,2,3)')"));
        query.exec(QString("insert into metadata values(4,'商丘',3,'建筑数据','bbox(1,2,2,3)')"));
        query.exec(QString("insert into metadata values(5,'南阳',3,'地块数据','bbox(1,2,2,3)')"));

        //3.创建密码表
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
        QDomElement root = doc.createElement(QString("地理数据清单"));
        doc.appendChild(root);

        QTextStream out(&file);
        doc.save(out,4);
        file.close();
        return true;
    }
};



#endif