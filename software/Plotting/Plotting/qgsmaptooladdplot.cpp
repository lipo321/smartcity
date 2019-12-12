#include "qgsmaptooladdplot.h"
#include "qgsproject.h"
#include "qgsvectordataprovider.h"
#include "qgsvectorlayer.h"

#include "qgsgeometry.h"
#include "qgslinestringv2.h"

#include "IC_QgsMapMouseEvent.h"

QgsMapToolAddPlot::QgsMapToolAddPlot(IC_QgsMapCanvas* canvas, CaptureMode mode )
    : QgsMapToolCapture( canvas, nullptr,  mode )
{
    mToolName = tr( "添加地图标绘demo" );
}

QgsMapToolAddPlot::~QgsMapToolAddPlot()
{

}
void QgsMapToolAddPlot::cadCanvasReleaseEvent( IC_QgsMapMouseEvent* e )
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

    if ( e->button() == Qt::LeftButton )
    {
        
        int error = addVertex( e->mapPoint(), e->mapPointMatch() );
        if ( error == 1 )
        {
            //current layer is not a vector layer
            return;
        }
        else if ( error == 2 )
        {
            //problem with coordinate transformation
            emit messageEmitted( tr( "不能将当前坐标点转换为图层坐标系" ), IC_QgsMessageBar::WARNING );
            //  m_pQgsMessageBar->pushMessage( tr( "" ), IC_QgsMessageBar::WARNING);
            return;
        }

        QgsFeature f( vlayer->fields(), 0 );
        QgsPoint src_point = e->mapPoint();

        QgsPoint point_1(src_point.x()+0.1,src_point.y()); 
        addVertex(point_1);

        QgsPoint point_2(src_point.x()+0.1,src_point.y()+0.02); 
        addVertex(point_2);

        QgsPoint point_3(src_point.x()+0.15,src_point.y()-0.01); 
        addVertex(point_3);

        QgsPoint point_4(src_point.x()+0.10,src_point.y()-0.04); 
        addVertex(point_4);

        QgsPoint point_5(src_point.x()+0.10,src_point.y()-0.01); 
        addVertex(point_5);

        QgsPoint point_6(src_point.x(),src_point.y()-0.01); 
        addVertex(point_6);

        addVertex(src_point);
        QgsLineStringV2* linstring = captureCurve()->curveToLine();;

        startCapturing();

        f.setGeometry(new QgsGeometry(linstring));

        vlayer->addFeature(f,false);
        stopCapturing();
        
        
        }
        
    
}

void QgsMapToolAddPlot::cadCanvasMoveEvent( IC_QgsMapMouseEvent* e ) 
{

}