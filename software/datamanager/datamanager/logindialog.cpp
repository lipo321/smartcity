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
    setWindowTitle(tr("µÇÂ¼Ò³Ãæ"));
    setBackgroundImage(QString::fromUtf8("D:\\work\\smartcity\\smartcity\\software\\datamanager\\datamanager\\Resources\\23.jpg"));
    ui->pwdLineEdit->setFocus();
    ui->loginBtn->setDefault(true);
}

LoginDialog::~LoginDialog()
{
    delete ui;
}

// µÇÂ¼°´Å¥
void LoginDialog::on_loginBtn_clicked()
{
    if (ui->pwdLineEdit->text().isEmpty()) {
        QMessageBox::information(this, tr("ÇëÊäÈëÃÜÂë"),
                                 tr("ÇëÏÈÊäÈëÃÜÂëÔÙµÇÂ¼£¡"), QMessageBox::Ok);
        ui->pwdLineEdit->setFocus();
    } else {
        QSqlQuery query;
        query.exec("select password from user");
        query.next();
        QString  pwd = query.value(0).toString();
        if (query.value(0).toString() == ui->pwdLineEdit->text()) {
            QDialog::accept();
        } else {
            QMessageBox::warning(this, tr("ÃÜÂë´íÎó"),
                                 tr("ÇëÊäÈëÕýÈ·µÄÃÜÂëÔÙµÇÂ¼£¡"), QMessageBox::Ok);
            ui->pwdLineEdit->clear();
            ui->pwdLineEdit->setFocus();
        }
    }
}

// ÍË³ö°´Å¥
void LoginDialog::on_quitBtn_clicked()
{
    QDialog::reject();
}


//ÉèÖÃ±³¾°Í¼Æ¬  path£ºÍ¼Æ¬Â·¾¶
void LoginDialog::setBackgroundImage(QString path)
{
    QPixmap pixmap = QPixmap(path).scaled(this->size());
    QPalette palette(this->palette());
    palette.setBrush(QPalette::Background, QBrush(pixmap));
    this->setPalette(palette);
}
