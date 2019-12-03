#include "logindialog.h"
#include "ui_logindialog.h"
#include <QMessageBox>
#include <QtSql/QtSql>
#include <QDebug>

LoginDialog::LoginDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::LoginDialog)
{
    ui->setupUi(this);
     this->setWindowIcon(QIcon("D:\\work\\smartcity\\smartcity\\software\\datamanager\\datamanager\\Resources\\windowIcon.jpg"));
    setFixedSize(400, 300);
    setWindowTitle(tr("��¼ҳ��"));
    setBackgroundImage(QString::fromUtf8("D:\\work\\smartcity\\smartcity\\software\\datamanager\\datamanager\\Resources\\23.jpg"));
    ui->pwdLineEdit->setFocus();
    ui->loginBtn->setDefault(true);
}

LoginDialog::~LoginDialog()
{
    delete ui;
}

// ��¼��ť
void LoginDialog::on_loginBtn_clicked()
{
    if (ui->pwdLineEdit->text().isEmpty()) {
        QMessageBox::information(this, tr("����������"),
                                 tr("�������������ٵ�¼��"), QMessageBox::Ok);
        ui->pwdLineEdit->setFocus();
    } else {
        QSqlQuery query;
        query.exec("select password from user");
        query.next();
        QString  pwd = query.value(0).toString();
        if (query.value(0).toString() == ui->pwdLineEdit->text()) {
            QDialog::accept();
        } else {
            QMessageBox::warning(this, tr("�������"),
                                 tr("��������ȷ�������ٵ�¼��"), QMessageBox::Ok);
            ui->pwdLineEdit->clear();
            ui->pwdLineEdit->setFocus();
        }
    }
}

// �˳���ť
void LoginDialog::on_quitBtn_clicked()
{
    QDialog::reject();
}


//���ñ���ͼƬ  path��ͼƬ·��
void LoginDialog::setBackgroundImage(QString path)
{
    QPixmap pixmap = QPixmap(path).scaled(this->size());
    QPalette palette(this->palette());
    palette.setBrush(QPalette::Background, QBrush(pixmap));
    this->setPalette(palette);
}
