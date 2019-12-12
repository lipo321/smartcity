#ifndef QGSMAPTOOLADDPLOT_H
#define QGSMAPTOOLADDPLOT_H

#include <QObject>
#include "qgsmaptoolcapture.h"

class QgsMapToolAddPlot :public QgsMapToolCapture
{
    Q_OBJECT

public:
    QgsMapToolAddPlot(IC_QgsMapCanvas* canvas, CaptureMode mode = CaptureNone);
    virtual ~QgsMapToolAddPlot();

    void cadCanvasReleaseEvent( IC_QgsMapMouseEvent* e ) override;
    void cadCanvasMoveEvent( IC_QgsMapMouseEvent* e ) override;
private:
    
};

#endif // QGSMAPTOOLADDPLOT_H
