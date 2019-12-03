#include "widget.h"
#include "ui_widget.h"
#include <QtSql/QtSql>
#include <QtGui>
#include "pieview.h"

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    this->setWindowIcon(QIcon("D:\\work\\smartcity\\smartcity\\software\\datamanager\\datamanager\\Resources\\windowIcon.jpg"));

    setFixedSize(750, 500);
    ui->stackedWidget->setCurrentIndex(0);

    QSqlQueryModel *typeModel = new QSqlQueryModel(this);
    typeModel->setQuery("select name from type");
    ui->geomTypeComboBox->setModel(typeModel);

    QSplitter *splitter = new QSplitter(ui->managePage);
    splitter->resize(700, 360);
    splitter->move(0, 50);

    splitter->addWidget(ui->toolBox);
    splitter->addWidget(ui->dailyList);
    splitter->setStretchFactor(0, 1);
    splitter->setStretchFactor(1, 1);

    on_sellCancelBtn_clicked();
    on_goodsCancelBtn_clicked();
    on_newCancelBtn_clicked();

    showDailyList();

    ui->typeComboBox->setModel(typeModel);
    ui->goodsTypeComboBox->setModel(typeModel);
    ui->newTypeComboBox->setModel(typeModel);

    createChartModelView();

}

Widget::~Widget()
{
    delete ui;
}

// �����ݵļ������͸ı�ʱ
void Widget::on_geomTypeComboBox_currentIndexChanged(QString type)
{
    if (type == "��ѡ������") {
        // ��������������״̬����
        on_sellCancelBtn_clicked();
    } else {
        ui->sellBrandComboBox->setEnabled(true);
        QSqlQueryModel *sellBrandModel = new QSqlQueryModel(this);
        sellBrandModel->setQuery(QString("select name from metadata where type=(select id from type where name='%1' )").arg(type));
        ui->sellBrandComboBox->setModel(sellBrandModel);
        ui->sellCancelBtn->setEnabled(true);
    }
}

// ������Ʒ������Ʒ���͸ı�ʱ
void Widget::on_goodsTypeComboBox_currentIndexChanged(QString type)
{
    if (type == "��ѡ������") {
        // ��������������״̬����
        on_goodsCancelBtn_clicked();
    } else {
        ui->goodsBrandComboBox->setEnabled(true);
        QSqlQueryModel *goodBrandModel = new QSqlQueryModel(this);
        goodBrandModel->setQuery(QString("select name from metadata where type=(select id from type where name='%1')").arg(type));
        ui->goodsBrandComboBox->setModel(goodBrandModel);
        ui->goodsCancelBtn->setEnabled(true);
    }
}

// ����Ʒ������͸ı�ʱ
void Widget::on_newTypeComboBox_currentIndexChanged(QString type)
{
    if (type == "��ѡ������") {
        // ��������������״̬����
        on_newCancelBtn_clicked();
    } else {
        ui->newBrandLineEdit->setEnabled(true);
        ui->newSumLineEdit_2->setEnabled(true);
        ui->newSumLineEdit->setEnabled(true);

        ui->newBrandLineEdit->setFocus();
    }
}

// ������Ʒ��Ʒ�Ƹı�ʱ
void Widget::on_sellBrandComboBox_currentIndexChanged(QString brand)
{
    ui->sellNumSpinBox->setValue(0);
    ui->sellNumSpinBox->setEnabled(false);
    ui->sellSumLineEdit->clear();
    ui->sellSumLineEdit->setEnabled(false);
    ui->sellOkBtn->setEnabled(false);

    QSqlQuery query;
    query.exec(QString("select geom_wkt from metadata where name='%1' and type=(select id from type where name='%2')")
               .arg(brand).arg(ui->geomTypeComboBox->currentText()));
    query.next();
    ui->sellPriceLineEdit->setEnabled(true);
    ui->sellPriceLineEdit->setReadOnly(true);
    ui->sellPriceLineEdit->setText(query.value(0).toString());

   
    query.exec(QString("select des from metadata where name='%1' and type=(select id from type where name='%2')")
        .arg(brand).arg(ui->geomTypeComboBox->currentText()));
    query.next();
    ui->sellSumLineEdit->setEnabled(true);
    ui->sellSumLineEdit->setReadOnly(true);
    QString mm = query.value(0).toString();
    ui->sellSumLineEdit->setText(query.value(0).toString());
    

    query.exec(QString("select num from metadata where name='%1' and type=(select id from type where name='%2')")
               .arg(brand).arg(ui->geomTypeComboBox->currentText()));
    query.next();
    int num = query.value(0).toInt();

    if (num == 0) {
        QMessageBox::information(this, tr("��ʾ"), tr("����Ʒ�Ѿ����꣡"), QMessageBox::Ok);
    } else {
        ui->sellNumSpinBox->setEnabled(true);
        ui->sellNumSpinBox->setMaximum(num);
        ui->sellLastNumLabel->setText(tr("ʣ��������%1").arg(num));
        ui->sellLastNumLabel->setVisible(true);
    }
}

// ������Ʒ����Ʒ�Ƹı�ʱ
void Widget::on_goodsBrandComboBox_currentIndexChanged(QString brand)
{
    ui->goodsNumSpinBox->setValue(0);
    ui->goodsNumSpinBox->setEnabled(true);
    ui->goodsSumLineEdit->clear();
    ui->goodsSumLineEdit->setEnabled(false);
    ui->goodsOkBtn->setEnabled(false);

    QSqlQuery query;
    query.exec(QString("select geom_wkt from metadata where name='%1' and type=(select id from type where name='%2')")
               .arg(brand).arg(ui->goodsTypeComboBox->currentText()));
    query.next();
    ui->goodsPriceLineEdit->setEnabled(true);
    ui->goodsPriceLineEdit->setReadOnly(true);
    ui->goodsPriceLineEdit->setText(query.value(0).toString());
}

// ����ƷƷ�Ƹı�ʱ
void Widget::on_newBrandLineEdit_textChanged(QString str)
{
    if (str == "") {
        ui->newCancelBtn->setEnabled(false);
        ui->newNumSpinBox->setEnabled(false);
        ui->newSumLineEdit->setEnabled(false);
        ui->newSumLineEdit->clear();
        ui->newOkBtn->setEnabled(false);
    } else {
        ui->newCancelBtn->setEnabled(true);
        ui->newNumSpinBox->setEnabled(true);
        ui->newSumLineEdit->setEnabled(true);
        ui->newSumLineEdit_2->setEnabled(true);
        ui->newOkBtn->setEnabled(true);
    }
}

// ������Ʒ�����ı�ʱ
void Widget::on_sellNumSpinBox_valueChanged(int value)
{
    if (value == 0) {
       // ui->sellSumLineEdit->clear();
       // ui->sellSumLineEdit->setEnabled(false);
        ui->sellOkBtn->setEnabled(false);
    } else {
       // ui->sellSumLineEdit->setEnabled(true);
        //ui->sellSumLineEdit->setReadOnly(true);
        qreal sum = value * ui->sellPriceLineEdit->text().toInt();
        //ui->sellSumLineEdit->setText(QString::number(sum));
        ui->sellOkBtn->setEnabled(true);
    }
}

// ������Ʒ��������ı�ʱ
void Widget::on_goodsNumSpinBox_valueChanged(int value)
{
    if (value == 0) {
        ui->goodsSumLineEdit->clear();
        ui->goodsSumLineEdit->setEnabled(false);
        ui->goodsOkBtn->setEnabled(false);
    } else {
        ui->goodsSumLineEdit->setEnabled(true);
        ui->goodsSumLineEdit->setReadOnly(true);
        qreal sum = value * ui->goodsPriceLineEdit->text().toInt();
        ui->goodsSumLineEdit->setText(QString::number(sum));
        ui->goodsOkBtn->setEnabled(true);
    }

}

// ����Ʒ���۸ı�ʱ
void Widget::on_newPriceSpinBox_valueChanged(int value)
{
    qreal sum = value * ui->newNumSpinBox->value();
    ui->newSumLineEdit->setEnabled(true);

    ui->newOkBtn->setEnabled(true);
}

// ����Ʒ����
void Widget::on_newNumSpinBox_valueChanged(int value)
{
    ui->newOkBtn->setEnabled(true);
    ui->newSumLineEdit->setEnabled(true);
    ui->newSumLineEdit_2->setEnabled(true);
}

// ������Ʒ��ȡ����ť
void Widget::on_sellCancelBtn_clicked()
{
    ui->geomTypeComboBox->setCurrentIndex(0);
    ui->sellBrandComboBox->clear();
    ui->sellBrandComboBox->setEnabled(false);
    ui->sellPriceLineEdit->clear();
    ui->sellPriceLineEdit->setEnabled(false);
    ui->sellNumSpinBox->setValue(0);
    ui->sellNumSpinBox->setEnabled(false);
    ui->sellSumLineEdit->clear();
    ui->sellSumLineEdit->setEnabled(false);
    ui->sellOkBtn->setEnabled(false);
    ui->sellCancelBtn->setEnabled(false);
    ui->sellLastNumLabel->setVisible(false);
}

// ������Ʒ����ȡ����ť
void Widget::on_goodsCancelBtn_clicked()
{
    ui->goodsTypeComboBox->setCurrentIndex(0);
    ui->goodsBrandComboBox->clear();
    ui->goodsBrandComboBox->setEnabled(false);
    ui->goodsPriceLineEdit->clear();
    ui->goodsPriceLineEdit->setEnabled(false);
    ui->goodsNumSpinBox->setValue(0);
    ui->goodsNumSpinBox->setEnabled(false);
    ui->goodsSumLineEdit->clear();
    ui->goodsSumLineEdit->setEnabled(false);
    ui->goodsOkBtn->setEnabled(false);
    ui->goodsCancelBtn->setEnabled(false);
}

// ����Ʒ����ȡ����ť
void Widget::on_newCancelBtn_clicked()
{
    ui->newTypeComboBox->setCurrentIndex(0);
    ui->newBrandLineEdit->clear();
    ui->newBrandLineEdit->setEnabled(false);
    ui->newNumSpinBox->setEnabled(false);
    ui->newNumSpinBox->setValue(1);
    ui->newSumLineEdit->setEnabled(false);
    ui->newOkBtn->setEnabled(false);
    ui->newCancelBtn->setEnabled(false);
}


// ������Ʒ��ȷ����ť
void Widget::on_sellOkBtn_clicked()
{
    QString type = ui->geomTypeComboBox->currentText();
    QString name = ui->sellBrandComboBox->currentText();
    int value = ui->sellNumSpinBox->value();
    // cellNumSpinBox�����ֵ������ǰ��ʣ����
    int last = ui->sellNumSpinBox->maximum() - value;

    QSqlQuery query;

    // ��ȡ��ǰ��������
    query.exec(QString("select allselled from metadata where name='%1' and id = (select id from type where name ='%2')")
               .arg(name).arg(type));
    query.next();
    int sell = query.value(0).toInt() + value;

    // �������
    QSqlDatabase::database().transaction();
    bool rtn = query.exec(
                QString("update metadata set allselled=%1,num=%2 where name='%3'and id = (select id from type where name ='%4')")
                .arg(sell).arg(last).arg(name).arg(type));

    if (rtn) {
        QSqlDatabase::database().commit();
        QMessageBox::information(this, tr("��ʾ"), tr("����ɹ���"), QMessageBox::Ok);
        writeXml();
        showDailyList();
    } else {
        QSqlDatabase::database().rollback();
        QMessageBox::information(this, tr("��ʾ"), tr("����ʧ�ܣ��޷��������ݿ⣡"), QMessageBox::Ok);
    }
    on_sellCancelBtn_clicked();
}

// ������Ʒ����ȷ����ť
void Widget::on_goodsOkBtn_clicked()
{
    QString type = ui->goodsTypeComboBox->currentText();
    QString name = ui->goodsBrandComboBox->currentText();
    int value = ui->goodsNumSpinBox->value();

    QSqlQuery query;
    // ��ȡ��ǰ������
   query.exec(QString("select num from metadata where name='%1' and type=(select id from type where name='%2')")
       .arg(name).arg(type));
    query.next();
    int sum = query.value(0).toInt() + value;


    // �������
    QSqlDatabase::database().transaction();
    bool rtn = query.exec(
                QString("update metadata set num=%1 where name='%2' and type= (select id from type where name='%3')")
                .arg(sum).arg(name).arg(type));
    QString hhh = QString("update metadata set num=%1 where name='%2' and type= (select id from type where name='%3')")
        .arg(sum).arg(name).arg(type);
    if (rtn) {
        QSqlDatabase::database().commit();
        QMessageBox::information(this, tr("��ʾ"), tr("���ɹ���"), QMessageBox::Ok);
    } else {
        QSqlDatabase::database().rollback();
        QMessageBox::information(this, tr("��ʾ"), tr("���ʧ�ܣ��޷��������ݿ⣡"), QMessageBox::Ok);
    }
    on_goodsCancelBtn_clicked();
}

// ����Ʒ��ȷ����ť
void Widget::on_newOkBtn_clicked()
{
    QString type = ui->newTypeComboBox->currentText();
    QString brand = ui->newBrandLineEdit->text();
    qint16 num = ui->newNumSpinBox->value();
    QString bbox = ui->newSumLineEdit_2->text();
    QString des =ui->newSumLineEdit->text();

    QSqlQuery query;
    query.exec("select id from metadata");
    query.last();
    int id = query.value(0).toInt() + 1;


    query.exec(QString("select id from type where name = '%1'").arg(type));
    query.next();
    int type_id = query.value(0).toInt();


    // �������
    QSqlDatabase::database().transaction();
    bool rtn = query.exec(QString("insert into metadata values(%1, '%2',%3, '%4', '%5',%6,%7)")
                .arg(id).arg(brand).arg(type_id).arg(des).arg(bbox).arg(num).arg(int(0)));
    if (rtn) {
        QSqlDatabase::database().commit();
        QMessageBox::information(this, tr("��ʾ"), tr("���ɹ���"), QMessageBox::Ok);
    } else {
        QSqlDatabase::database().rollback();
        QMessageBox::information(this, tr("��ʾ"), tr("���ʧ�ܣ��޷��������ݿ⣡"), QMessageBox::Ok);
    }

    on_newCancelBtn_clicked();
}

// ��ȡ��ǰ�����ڻ���ʱ��
QString Widget::getDateTime(Widget::DateTimeType type)
{
    QDateTime datetime = QDateTime::currentDateTime();
    QString date = datetime.toString("yyyy-MM-dd");
    QString time = datetime.toString("hh:mm");
    QString dateAndTime = datetime.toString("yyyy-MM-dd dddd hh:mm");
    if(type == Date) return date;
    else if(type == Time) return time;
    else return dateAndTime;
}

// ��ȡXML�ĵ�
bool Widget::docRead()
{
    QFile file("data.xml");
    if(!file.open(QIODevice::ReadOnly))
        return false;
    if(!doc.setContent(&file)){
        file.close();
        return false;
    }
    file.close();
    return true;
}

// д��xml�ĵ�
bool Widget::docWrite()
{
    QFile file("data.xml");
    if(!file.open(QIODevice::WriteOnly | QIODevice::Truncate))
        return false;
    QTextStream out(&file);
    doc.save(out,4);
    file.close();
    return true;
}

// �����ۼ�¼д���ĵ�
void Widget::writeXml()
{
    // �ȴ��ļ���ȡ
    if (docRead()) {
        QString currentDate = getDateTime(Date);
        QDomElement root = doc.documentElement();
        // �����Ƿ������ڽڵ���д���
        if (!root.hasChildNodes()) {
            QDomElement date = doc.createElement(QString("����"));
            QDomAttr curDate = doc.createAttribute("date");
            curDate.setValue(currentDate);
            date.setAttributeNode(curDate);
            root.appendChild(date);
            createNodes(date);
        } else {
            QDomElement date = root.lastChild().toElement();
            // �����Ƿ��Ѿ��н�������ڽڵ���д���
            if (date.attribute("date") == currentDate) {
                createNodes(date);
            } else {
                QDomElement date = doc.createElement(QString("����"));
                QDomAttr curDate = doc.createAttribute("date");
                curDate.setValue(currentDate);
                date.setAttributeNode(curDate);
                root.appendChild(date);
                createNodes(date);
            }
        }
        // д�뵽�ļ�
        docWrite();
    }
}

// �����ڵ�
void Widget::createNodes(QDomElement &date)
{
    QDomElement time = doc.createElement(QString("ʱ��"));
    QDomAttr curTime = doc.createAttribute("time");
    curTime.setValue(getDateTime(Time));
    time.setAttributeNode(curTime);
    date.appendChild(time);

    QDomElement type = doc.createElement(QString("����"));
    QDomElement brand = doc.createElement(QString("Ʒ��"));
    QDomElement price = doc.createElement(QString("����"));
    QDomElement num = doc.createElement(QString("����"));
    QDomElement sum = doc.createElement(QString("���"));
    QDomText text;
    text = doc.createTextNode(QString("%1")
                              .arg(ui->geomTypeComboBox->currentText()));
    type.appendChild(text);
    text = doc.createTextNode(QString("%1")
                              .arg(ui->sellBrandComboBox->currentText()));
    brand.appendChild(text);
    text = doc.createTextNode(QString("%1")
                              .arg(ui->sellPriceLineEdit->text()));
    price.appendChild(text);
    text = doc.createTextNode(QString("%1")
                              .arg(ui->sellNumSpinBox->value()));
    num.appendChild(text);
    text = doc.createTextNode(QString("%1")
                              .arg(ui->sellSumLineEdit->text()));
    sum.appendChild(text);

    time.appendChild(type);
    time.appendChild(brand);
    time.appendChild(price);
    time.appendChild(num);
    time.appendChild(sum);
}


// ��ʾ�������嵥
void Widget::showDailyList()
{
    ui->dailyList->clear();
    if (docRead()) {
        QDomElement root = doc.documentElement();
        QString title = root.tagName();
        QListWidgetItem *titleItem = new QListWidgetItem; 
        titleItem->setText(QString("-----%1-----").arg(title));
        titleItem->setTextAlignment(Qt::AlignCenter);
        ui->dailyList->addItem(titleItem);

        if (root.hasChildNodes()) {
            QString currentDate = getDateTime(Date);
            QDomElement dateElement = root.lastChild().toElement();
            QString date = dateElement.attribute("date");
            if (date == currentDate) {
                ui->dailyList->addItem("");
                ui->dailyList->addItem(QString("���ڣ�%1").arg(date));
                ui->dailyList->addItem("");
                QDomNodeList children = dateElement.childNodes();
                // �����������۵�������Ʒ
                for (int i=0; i<children.count(); i++) {
                    QDomNode node = children.at(i);
                    QString time = node.toElement().attribute("time");
                    QDomNodeList list = node.childNodes();
                    QString type = list.at(0).toElement().text();
                    QString brand = list.at(1).toElement().text();
                    QString price = list.at(2).toElement().text();
                    QString num = list.at(3).toElement().text();
                    QString sum = list.at(4).toElement().text();

                    QString str = time + " ���� " + brand + type
                            + " " + num + "�ݣ� " + "��Χ��" + price
                            + "�� ������" + sum ;
                    QListWidgetItem *tempItem = new QListWidgetItem;
                    tempItem->setText("**************************");
                    tempItem->setTextAlignment(Qt::AlignCenter);
                    ui->dailyList->addItem(tempItem);
                    ui->dailyList->addItem(str);
                }
            }
        }
    }
}


// �������ۼ�¼ͼ���ģ�ͺ���ͼ
void Widget::createChartModelView()
{
    chartModel = new QStandardItemModel(this);
    chartModel->setColumnCount(2);
    chartModel->setHeaderData(0, Qt::Horizontal, QString("Ʒ��"));
    chartModel->setHeaderData(1, Qt::Horizontal, QString("��������"));

    QSplitter *splitter = new QSplitter(ui->chartPage);
    splitter->resize(700, 320);
    splitter->move(0, 80);
    QTableView *table = new QTableView;
    PieView *pieChart = new PieView;
    splitter->addWidget(table);
    splitter->addWidget(pieChart);
    splitter->setStretchFactor(0, 1);
    splitter->setStretchFactor(1, 2);

    table->setModel(chartModel);
    pieChart->setModel(chartModel);

    QItemSelectionModel *selectionModel = new QItemSelectionModel(chartModel);
    table->setSelectionModel(selectionModel);
    pieChart->setSelectionModel(selectionModel);
}

// ��ʾ���ۼ�¼ͼ��
void Widget::showChart()
{
    QString type = ui->typeComboBox->currentText();
    if (type==QString::fromLocal8Bit("��������"))
    {
        return;
    }
    QSqlQuery query;
    query.exec(QString("select name,des from metadata where type=(select id from type where name='%1' )")
               .arg(type));

  //  chartModel->removeRows(0, chartModel->rowCount(QModelIndex()), QModelIndex());

    int row = 0;

    while(query.next()) {
        int r = qrand() % 256;
        int g = qrand() % 256;
        int b = qrand() % 256;

        chartModel->insertRows(row, 1, QModelIndex());

        chartModel->setData(chartModel->index(row, 0, QModelIndex()),
                            query.value(0).toString());
        chartModel->setData(chartModel->index(row, 1, QModelIndex()),
                            query.value(1).toInt());
        chartModel->setData(chartModel->index(row, 0, QModelIndex()),
                            QColor(r, g, b), Qt::DecorationRole);
        row++;
    }
}

// ����ͳ��ҳ�������ѡ���
void Widget::on_typeComboBox_currentIndexChanged(QString type)
{
    if (type != "��ѡ������")
        showChart();
}

// ������ʾ��ť
void Widget::on_updateBtn_clicked()
{
    if (ui->typeComboBox->currentText() != "��ѡ������")
        showChart();
}

// �������ݹ���ť
void Widget::on_manageBtn_clicked()
{
    ui->stackedWidget->setCurrentIndex(0);
}

// ������������ͳ�ư�ť
void Widget::on_chartBtn_clicked()
{
    ui->stackedWidget->setCurrentIndex(1);
}

// �޸����밴ť
void Widget::on_passwordBtn_clicked()
{
    ui->stackedWidget->setCurrentIndex(2);
}

// �޸�����ȷ����ť
void Widget::on_changePwdBtn_clicked()
{
    if (ui->oldPwdLineEdit->text().isEmpty() ||
            ui->newPwdLineEdit->text().isEmpty()) {
        QMessageBox::warning(this, tr("����"), tr("�뽫��Ϣ��д������"),
                             QMessageBox::Ok);
    } else {
        QSqlQuery query;
        query.exec("select password from user");
        query.next();
        if (query.value(0).toString() == ui->oldPwdLineEdit->text()) {
            bool temp = query.exec(QString("update user set password='%1' where password='%2'")
                                   .arg(ui->newPwdLineEdit->text()).arg(ui->oldPwdLineEdit->text()));
            if (temp) {
                QMessageBox::information(this, tr("��ʾ"), tr("�����޸ĳɹ���"),
                                         QMessageBox::Ok);
                ui->oldPwdLineEdit->clear();
                ui->newPwdLineEdit->clear();
            } else {
                QMessageBox::information(this, tr("��ʾ"), tr("�����޸�ʧ�ܣ��޷��������ݿ⣡"),
                                         QMessageBox::Ok);
            }
        } else {
            QMessageBox::warning(this, tr("����"), tr("ԭ���������������д��"),
                                 QMessageBox::Ok);
            ui->oldPwdLineEdit->clear();
            ui->newPwdLineEdit->clear();
            ui->oldPwdLineEdit->setFocus();
        }
    }

}


























