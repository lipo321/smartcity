#ifndef PLOTTING_H
#define PLOTTING_H

#include <qgsmessageviewer.h>
#include <qgsdockwidget.h>
#include <qgsmaplayer.h>

#include "IC_Plugin.h"
#include "IC_MainUI.h"
#include "plotting_global.h"
#include "IC_QgsMapTool.h"

#include <QAction>
#include <QToolBar>
using namespace onegis::psdf::ui;

class  Plotting:public QObject,public IC_Plugin
{
    Q_OBJECT
public:
    Plotting(IC_MainUI *ui );
    virtual ~Plotting();

    //插件类继承函数
public:
    virtual bool onLoad(QString &errMsg);
    virtual void onUnload();
    virtual bool unloadNotify( const QString plugin);
    virtual void onExit();
private:
    void createActions( );
    void clearActions( );
    void createTools( );
    void clearTools( );

public slots:
    /* 工具UI更新 */
    void onUpdateMapToolStatus(QgsMapLayer *layer);

    /*添加标绘*/
    void addPolt();
    void activeAddPolt();
    void deactiveAddPolt();

    /*添加SVG*/
    void addSvg();
    void activeAddSvg();
    void deactiveAddSvg();
private:
    IC_MainUI   *m_pMainUI;
    QgsDockWidget *mDock;
    QToolBar           *mToolBar;

    QAction          *mActionAddPlot;
    IC_QgsMapTool*  m_pAddPoltDemo;

    QAction       *mActionAddSvg;
    IC_QgsMapTool *m_pAddSvg;
};

#endif // PLOTTING_H
