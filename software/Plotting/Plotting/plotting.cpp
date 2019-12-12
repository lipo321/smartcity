#include "plotting.h"
#include "C_Singleton.h"

#include "onegis_plugin_2DMapVisual_def.h"
#include "IC_Logger.h"
#include <qgsmaplayer.h>
#include <qgsvectordataprovider.h>

#include "svgannotationimageitem.h"
#include "qgsmaptooladdplot.h"
#include "addsvgtool.h"

using namespace onegis::psdf::core;

static const QString scPluginID = QString("onegis_plugin_Plotting");
static const QString scPluginName = QString::fromLocal8Bit("OneGIS_2DMapTool_Plotting");
static const QString scPluginVersion = QString("1.0.0");
static const QString scPluginDesc = QString::fromLocal8Bit("二维标图");
static const QString scPluginAuthor = QString("OneGIS Dev Team");
static const int scPluginType = IC_Plugin::UI;
static QPixmap scPluginIcon;

Plotting::Plotting(IC_MainUI *ui)
    :m_pMainUI(ui)
{
     C_Singleton::setMainUI( ui );
}

Plotting::~Plotting()
{

}
void Plotting::createActions()
{
    //添加要素Action
    mActionAddPlot = new QAction(  QIcon( "./sources/jiantou.svg" ), QObject::tr("添加标绘"), 0 );
    mActionAddPlot->setCheckable(true);
    connect( mActionAddPlot, SIGNAL( triggered() ), this, SLOT( addPolt( ) ) );

    //添加svg
    mActionAddSvg= new QAction(  QIcon( "./sources/tower.svg" ), QObject::tr("添加图标"), 0 );
    mActionAddSvg->setCheckable(true);
    connect( mActionAddSvg, SIGNAL( triggered() ), this, SLOT( addSvg( ) ) );


    mToolBar = new QToolBar(tr("标绘工具栏"));

    mToolBar->addAction( mActionAddPlot );
    mToolBar->addAction(mActionAddSvg);
    m_pMainUI->addCustomToolBar( Qt::TopToolBarArea, mToolBar, true );
}

void Plotting::clearActions()
{
   disconnect( mActionAddPlot, SIGNAL( triggered() ), this, SLOT( addPolt( ) ) );
   delete mActionAddPlot;

   disconnect( mActionAddSvg, SIGNAL( triggered() ), this, SLOT( addSvg( ) ) );
   delete mActionAddSvg;
}

void Plotting::addPolt()
{
    IC_2DMapVisualInterfacePtr p = C_Singleton::mapvisualInterface();
    p->mapCanvas()->setMapTool( m_pAddPoltDemo );
}

void Plotting::addSvg()
{
    IC_2DMapVisualInterfacePtr p = C_Singleton::mapvisualInterface();
    p->mapCanvas()->setMapTool( m_pAddSvg );
}

void Plotting::createTools()
{

    IC_2DMapVisualInterfacePtr p = C_Singleton::mapvisualInterface();
    mDock = new QgsDockWidget( tr( "标绘功能" ), m_pMainUI->mainWindow() );
    mDock->setObjectName( "标绘功能" );
    m_pMainUI->addDockWidget(Qt::BottomDockWidgetArea , mDock , true , QString( "标绘功能" ) );
    //mLogDock->hide();
   // QgsMessageLog::instance()->logMessage(QString("message log"));

    m_pAddPoltDemo = new QgsMapToolAddPlot(p->mapCanvas());
    connect( m_pAddPoltDemo, SIGNAL( activated() ), this, SLOT( activeAddPolt() ) );
    connect( m_pAddPoltDemo, SIGNAL( deactivated() ), this, SLOT( deactiveAddPolt() ) );

    m_pAddSvg = new AddSvgTool(p->mapCanvas());
    connect( m_pAddSvg, SIGNAL( activated() ), this, SLOT( activeAddSvg() ) );
    connect( m_pAddSvg, SIGNAL( deactivated() ), this, SLOT( deactiveAddSvg() ) );
}


void Plotting::activeAddSvg()
{
    mActionAddPlot->setChecked(true);
}

void Plotting::deactiveAddSvg()
{
    mActionAddPlot->setChecked(false);
}


void Plotting::activeAddPolt()
{
    mActionAddPlot->setChecked(true);
}
void Plotting::deactiveAddPolt()
{
    mActionAddPlot->setChecked(false);
}
void Plotting::clearTools()
{
    m_pMainUI->removeDockWidget( mDock );
    if(mDock!=nullptr)
    {
        delete mDock;
    }
}


bool Plotting::onLoad(QString &errMsg)
{
    if ( !C_Singleton::checkInterface() )
    {
        errMsg= QString("Error load plugin OneGIS_2Dmap_FeaturesEditors");
        OGS_ERROR("Error load plugin %s", "OneGIS_2Dmap_FeaturesEditors");
        return false;
    }

    //创建添加要素工具
    createActions();
    createTools();

    /* 更新工具UI */
    IC_2DMapVisualInterfacePtr p = C_Singleton::mapvisualInterface();
    connect( p->mapCanvas(), SIGNAL( updateToolStatus(QgsMapLayer *) ), this, SLOT( onUpdateMapToolStatus(QgsMapLayer *) ) );

    /* 更新工具UI状态 */
    onUpdateMapToolStatus( p->mapCanvas()->currentLayer() );

    return true;
}

void Plotting::onUnload()
{
    disconnect( C_Singleton::mapvisualInterface()->mapCanvas(), nullptr, this, nullptr );
    clearTools();
    clearActions();
    m_pMainUI = nullptr;
}

bool Plotting::unloadNotify(const QString plugin)
{
    if (  0 == plugin.compare(QString(ONEGIS_PLUGIN_2DMAPVISUAL_ID)) )
    {
        return true;
    }
    return false;
}

void Plotting::onExit()
{
    onUnload();
}



/************************************************
 * 更新所有插件UI的状态 
 ************************************************/
void Plotting::onUpdateMapToolStatus(QgsMapLayer *layer)
{
    if ( !layer )
    {
        return;
    }

    if ( layer->type() == QgsMapLayer::VectorLayer )
    {

        QgsVectorLayer* vlayer = qobject_cast<QgsVectorLayer *>( layer );
        QgsVectorDataProvider* dprovider = vlayer->dataProvider();
		bool isEditable = vlayer->isEditable();
        bool layerHasSelection = vlayer->selectedFeatureCount() > 0;

        if ( dprovider )
        {
            bool canChangeGeometry = dprovider->capabilities() & QgsVectorDataProvider::ChangeGeometries;
            bool canDeleteFeatures = dprovider->capabilities() & QgsVectorDataProvider::DeleteFeatures;
            bool canChangeAttributes = dprovider->capabilities() & QgsVectorDataProvider::ChangeAttributeValues;

            bool canAddFeatures = dprovider->capabilities() & QgsVectorDataProvider::AddFeatures;


            if ( isEditable && canChangeAttributes )
            {

            }
            else
            {

            }

            if ( vlayer->geometryType() == QGis::Point )
            {

            }
            else if ( vlayer->geometryType() == QGis::Line )
            {

            }
            else if ( vlayer->geometryType() == QGis::Polygon )
            {

            }
            else if ( vlayer->geometryType() == QGis::NoGeometry )
            {

            }
        }
    }
    else if ( layer->type() == QgsMapLayer::RasterLayer )
    {

    }
}


/************************************************
 *    导出函数
 ************************************************/
extern "C" MAPTOOL_PLOTTING_EXPORT IC_Plugin* CreatePlugin( IC_MainUI *ui)
{
    return (new Plotting(ui));
}
extern "C" MAPTOOL_PLOTTING_EXPORT void ReleasePlugin(IC_Plugin *plugin)
{
    delete (Plotting*)plugin;
}
extern "C" MAPTOOL_PLOTTING_EXPORT QString PluginName()
{
    return scPluginName;
}
extern "C" MAPTOOL_PLOTTING_EXPORT QString PluginID()
{
    return scPluginID;
}
extern "C" MAPTOOL_PLOTTING_EXPORT QString PluginVersion()
{
    return scPluginVersion;
}
extern "C" MAPTOOL_PLOTTING_EXPORT int PluginType()
{
    return scPluginType;
}
extern "C" MAPTOOL_PLOTTING_EXPORT QString PluginDescription()
{
    return scPluginDesc;
}
extern "C" MAPTOOL_PLOTTING_EXPORT QString PluginAuthor()
{
    return scPluginAuthor;
}
extern "C" MAPTOOL_PLOTTING_EXPORT QPixmap& PluginIcon()
{
    scPluginIcon.load(":/2dmaptool_featureseditor/image/logviewer.png");
    return scPluginIcon;
    //return QPixmap(QString(":/2dmaptool_featureseditor/image/logviewer.png"));
}
extern "C" MAPTOOL_PLOTTING_EXPORT void  PluginModules(QList<QString> &modules)
{
    /* 返回本插件中包含的模块名称，如果插件中包含有模块必须实现. */
}
extern "C" MAPTOOL_PLOTTING_EXPORT void DependPlugins (QList<T_DependPlugin> &depPlugins)
{
    T_DependPlugin pluginInfo;
    pluginInfo.id = QString(ONEGIS_PLUGIN_2DMAPVISUAL_ID);
    pluginInfo.name = QString::fromLocal8Bit(ONEGIS_PLUGIN_2DMAPVISUAL_NAME);
    pluginInfo.version = QString(ONEGIS_PLUGIN_2DMAPVISUAL_VERSION);
    depPlugins.append(pluginInfo);
}