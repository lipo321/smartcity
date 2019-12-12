#ifndef SVGANNOTATIONIMAGEITEM_H
#define SVGANNOTATIONIMAGEITEM_H

#include "qgsannotationitem.h"

#include <QDomDocument>
#include <QPainter>
#include <QSvgRenderer>
class SvgAnnotationImageItem :public QObject, public QgsAnnotationItem
{
    Q_OBJECT

public:
    SvgAnnotationImageItem( IC_QgsMapCanvas* canvas );
    virtual ~SvgAnnotationImageItem();
public:
    void writeXML(QDomDocument& doc) const override;
    void readXML(const QDomDocument& doc,const QDomElement& itemElem);
    void paint(QPainter* painter);
    void setFilePath(const QString& file);
    QString filePath() const ;
   
private:
    QSvgRenderer* m_pSvgRender;
    QString       mFilePath;
    
};

#endif // SVGANNOTATIONIMAGEITEM_H
