#ifndef C_SINGLETON_H
#define C_SINGLETON_H

#include "IC_2DMapVisual_Interface.h"

#include "IC_MainUI.h"

using namespace onegis::psdf::ui;

class C_Singleton
{
public:
    static C_Singleton* instance();
    static void clearInstance();

    static bool checkInterface();
    static bool getMapVisualInterface();


    virtual ~C_Singleton();

    static IC_2DMapVisualInterfacePtr mapvisualInterface();


    static void setMainUI(IC_MainUI *ui);
    static IC_MainUI* mainUI();

    static int messageTimeout();

private:
    C_Singleton();

    static C_Singleton              *m_pInstance;
    static IC_MainUI                *m_pMainUI;
    static IC_2DMapVisualInterfacePtr         m_pMapVisualInterface;
};

#endif