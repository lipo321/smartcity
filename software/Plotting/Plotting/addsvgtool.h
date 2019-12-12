#ifndef ADDSVGTOOL_H
#define ADDSVGTOOL_H

#include <QObject>
#include "qgsmaptoolcapture.h"

class AddSvgTool : public QgsMapToolCapture
{
    Q_OBJECT

public:
    AddSvgTool(IC_QgsMapCanvas* canvas, CaptureMode mode = CaptureNone);
    virtual ~AddSvgTool();

    void cadCanvasReleaseEvent( IC_QgsMapMouseEvent* e ) override;
private:
    
};

#endif // ADDSVGTOOL_H
