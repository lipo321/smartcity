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

    setFixedSize(400, 300);
    setWindowTitle(tr("��¼"));
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
