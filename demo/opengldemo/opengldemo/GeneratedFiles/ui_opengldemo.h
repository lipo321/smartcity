/********************************************************************************
** Form generated from reading UI file 'opengldemo.ui'
**
** Created by: Qt User Interface Compiler version 4.8.6
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_OPENGLDEMO_H
#define UI_OPENGLDEMO_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QHeaderView>
#include <QtGui/QMainWindow>
#include <QtGui/QMenuBar>
#include <QtGui/QStatusBar>
#include <QtGui/QToolBar>
#include <QtGui/QVBoxLayout>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_opengldemoClass
{
public:
    QWidget *centralWidget;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *opengldemoClass)
    {
        if (opengldemoClass->objectName().isEmpty())
            opengldemoClass->setObjectName(QString::fromUtf8("opengldemoClass"));
        opengldemoClass->resize(639, 407);
        centralWidget = new QWidget(opengldemoClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        verticalLayoutWidget = new QWidget(centralWidget);
        verticalLayoutWidget->setObjectName(QString::fromUtf8("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(20, 10, 601, 321));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(0);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setSizeConstraint(QLayout::SetNoConstraint);
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        opengldemoClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(opengldemoClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 639, 23));
        opengldemoClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(opengldemoClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        opengldemoClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(opengldemoClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        opengldemoClass->setStatusBar(statusBar);

        retranslateUi(opengldemoClass);

        QMetaObject::connectSlotsByName(opengldemoClass);
    } // setupUi

    void retranslateUi(QMainWindow *opengldemoClass)
    {
        opengldemoClass->setWindowTitle(QApplication::translate("opengldemoClass", "opengldemo", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class opengldemoClass: public Ui_opengldemoClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_OPENGLDEMO_H
