#ifndef THREE_SC_WIDGWT
#define THREE_SC_WIDGWT
#include "SCWidget.h"

class ThreeSCWidget : public SCWidget
{
public:
    ThreeSCWidget(QWidget* parent,const char* name,bool isFullScreen);
    virtual ~ThreeSCWidget(void);

    virtual void paintGL();
};

#endif