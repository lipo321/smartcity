#include "svgannotationimageitem.h"
#include <qgsproject.h>

SvgAnnotationImageItem::SvgAnnotationImageItem(IC_QgsMapCanvas* canvas)
    : QgsAnnotationItem(canvas)
{
    m_pSvgRender = new QSvgRenderer();
}

SvgAnnotationImageItem::~SvgAnnotationImageItem()
{

}


void SvgAnnotationImageItem::writeXML(QDomDocument& doc) const
{
    QDomElement docElement = doc.documentElement();
    if (docElement.isNull())
    {
        return;
    }

    QDomElement svgAnnotationElem = doc.createElement( "SVGAnnotationItem" );
    svgAnnotationElem.setAttribute( "file", QgsProject::instance()->writePath( mFilePath ) );
    _writeXML( doc, svgAnnotationElem );
    docElement.appendChild( svgAnnotationElem );
}
void SvgAnnotationImageItem::readXML(const QDomDocument& doc,const QDomElement& itemElem)
{
    QString filePath = QgsProject::instance()->readPath(itemElem.attribute("file"));
    setFilePath(filePath);
    QDomElement annotationElem = itemElem.firstChildElement("AnnotationItem");
    if (!annotationElem.isNull())
    {
        _readXML(doc,annotationElem);
    }
}
void SvgAnnotationImageItem::paint(QPainter* painter)
{
    if (!painter)
    {
        return;
    }

    QRect viewBox = m_pSvgRender->viewBox();
    if ( viewBox.isValid() )
    {
        double widthRatio = mFrameSize.width() / viewBox.width();
        double heightRatio = mFrameSize.height() / viewBox.height();
        double renderWidth = 0;
        double renderHeight = 0;
        if ( widthRatio <= heightRatio )
        {
            renderWidth = mFrameSize.width();
            renderHeight = viewBox.height() * mFrameSize.width() / viewBox.width();
        }
        else
        {
            renderHeight = mFrameSize.height();
            renderWidth = viewBox.width() * mFrameSize.height() / viewBox.height();
        }
        m_pSvgRender->render( painter, QRectF( mOffsetFromReferencePoint.x(), mOffsetFromReferencePoint.y(), renderWidth,
            renderHeight ) );
    }
    if ( isSelected() )
    {
        drawSelectionBoxes( painter );
    }
}
void SvgAnnotationImageItem::setFilePath(const QString& file)
{
    mFilePath =file;
    m_pSvgRender->load(mFilePath);
}
QString SvgAnnotationImageItem::filePath() const 
{
    return mFilePath;
}