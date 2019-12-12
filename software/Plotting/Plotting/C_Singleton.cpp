#include "C_Singleton.h"
#include "onegis_plugin_2DMapVisual_def.h"
#include "IC_SysKernel.h"
#include "IC_Logger.h"
#include <QSettings>


C_Singleton * C_Singleton::m_pInstance = nullptr;
IC_MainUI * C_Singleton::m_pMainUI = nullptr;
IC_2DMapVisualInterfacePtr C_Singleton::m_pMapVisualInterface = nullptr;


C_Singleton::C_Singleton()
{
}

C_Singleton::~C_Singleton()
{
}

C_Singleton* C_Singleton::instance()
{
    if (nullptr != m_pInstance)
    {
        m_pInstance = new C_Singleton();
    }
    return m_pInstance;
}

void C_Singleton::clearInstance()
{
    if (nullptr != m_pInstance)
    {
        delete m_pInstance;
        m_pInstance = nullptr;
    }
}

void C_Singleton::setMainUI(IC_MainUI *ui)
{
    m_pMainUI = ui;
}

IC_MainUI* C_Singleton::mainUI()
{
    return m_pMainUI;
}
bool C_Singleton::checkInterface()
{
    if ( !getMapVisualInterface()) return false;
    return true;
}

bool C_Singleton::getMapVisualInterface()
{
    ModulePtr p = G_GetSysKernel().queryModule(ONEGIS_PLUGIN_2DMAPVISUAL_MODULEID);
    if ( !p ) 
    {
        OGS_ERROR("Missing module %s", ONEGIS_PLUGIN_2DMAPVISUAL_MODULEID);
        return false;
    }
    m_pMapVisualInterface = p->getInterface<IC_2DMapVisual_Interface>(ONEGIS_PLUGIN_2DMAPVISUAL_INTERFACEID);
    if ( !m_pMapVisualInterface )
    {
        OGS_ERROR("Missing interface %s", ONEGIS_PLUGIN_2DMAPVISUAL_INTERFACEID);
        return false;
    }
    return true;
}



IC_2DMapVisualInterfacePtr C_Singleton::mapvisualInterface()
{
    return m_pMapVisualInterface;
}

int C_Singleton::messageTimeout()
{
    QSettings settings;
    return settings.value( "/qgis/messageTimeout", 5 ).toInt();
}