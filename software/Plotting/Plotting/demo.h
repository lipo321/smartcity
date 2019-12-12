#ifndef DEMO_H
#define DEMO_H

#include <QWidget>
#include "ui_demo.h"

class Demo : public QWidget
{
    Q_OBJECT

public:
    Demo(QWidget *parent = 0);
    ~Demo();

private:
    Ui::Demo ui;
};

#endif // DEMO_H
