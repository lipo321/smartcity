#include "addsvgtool.h"

#include <qgsvectorlayer.h>
#include <qgsrendercontext.h>

#include "IC_2DMapVisual_Interface.h"
#include "svgannotationimageitem.h"
#include "C_Singleton.h"
#include "IC_QgsMapMouseEvent.h"

AddSvgTool::AddSvgTool(IC_QgsMapCanvas* canvas, CaptureMode mode)
    : QgsMapToolCapture(canvas,nullptr,mode)
{
    mToolName = tr("添加POI图标");

}

AddSvgTool::~AddSvgTool()
{

}


void AddSvgTool::cadCanvasReleaseEvent( IC_QgsMapMouseEvent* e )
{

    //清除原有消息
    emit messageDiscarded();

    //check if we operate on a vector layer
    QgsVectorLayer *vlayer = currentVectorLayer();

    if ( !vlayer )
    {
        notifyNotVectorLayer();
        return;
    }

    if ( !vlayer->isEditable() )
    {
        notifyNotEditableLayer();
        return;
    }

    if ( vlayer && vlayer->geometryType() == QGis::Point )
    {
        IC_2DMapVisualInterfacePtr p = C_Singleton::mapvisualInterface();
        SvgAnnotationImageItem* item = new SvgAnnotationImageItem(p->mapCanvas());
        item->setFrameSize(QSizeF(64,64));
        item->setFrameBorderWidth(0);
        item->setMapPosition(e->mapPoint());
        item->setFilePath("./sources/tower.svg");
        p->mapCanvas()->scene()->addItem(item);
    }
}
