/***************************************************************************
    qgsmaptoolcapture.cpp  -  map tool for capturing points, lines, polygons
    ---------------------
    begin                : January 2006
    copyright            : (C) 2006 by Martin Dobias
    email                : wonder.sk at gmail dot com
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

#include "qgsmaptoolcapture.h"
#include "qgscursors.h"
#include "qgsgeometryvalidator.h"
#include "qgslinestringv2.h"
#include "qgslogger.h"
#ifdef ONEGIS_MAPTOOL
#include "qgsmapcanvas.h"
#include "qgslayertreeview.h"
#include "qgsmapmouseevent.h"
#include "qgsmapcanvastracer.h"
#else
#include "C_Singleton.h"
#include "IC_QgsMapCanvas.h"
#include "IC_QgsMapCanvasTracer.h"
#include "IC_QgsMapMouseEvent.h"
#endif
#include "qgsmaprenderer.h"
#include "qgspolygonv2.h"
#include "qgsrubberband.h"
#include "qgsvectorlayer.h"
#include "qgsvertexmarker.h"

#include <QCursor>
#include <QPixmap>
#include <QMouseEvent>
#include <QStatusBar>
#include <QSettings>
#include <QAction>

int QgsMapToolCapture::m_iFlowDInterval = 0;
int QgsMapToolCapture::m_iFlowTInterval = 0;

QgsMapToolCapture::QgsMapToolCapture( IC_QgsMapCanvas* canvas, QgsAdvancedDigitizingDockWidget* cadDockWidget, CaptureMode mode )
    : QgsMapToolAdvancedDigitizing( canvas, cadDockWidget )
    , mRubberBand( nullptr )
    , mTempRubberBand( nullptr )
    , mValidator( nullptr )
    , mSnappingMarker( nullptr )
    , m_int64Time(0)
#ifdef Q_OS_WIN
    , mSkipNextContextMenuEvent( 0 )
#endif
{
  mCaptureMode = mode;

  // enable the snapping on mouse move / release
  mSnapOnMove = true;
  mSnapOnRelease = true;
  mSnapOnDoubleClick = false;
  mSnapOnPress = false;

  mCaptureModeFromLayer = mode == CaptureNone;
  mCapturing = false;

  QPixmap mySelectQPixmap = QPixmap(( const char ** ) capture_point_cursor );
  setCursor( QCursor( mySelectQPixmap, 8, 8 ) );

  connect( canvas, SIGNAL( currentLayerChanged( QgsMapLayer * ) ),
           this, SLOT( currentLayerChanged( QgsMapLayer * ) ) );


  //add by lipo 20170308
  if( m_iFlowDInterval<=0 || m_iFlowTInterval<=0)
  {
      QSettings settings;
      m_iFlowDInterval = settings.value( "/qgis/digitizing/mouseflow_d_interval",10).toInt();
      m_iFlowTInterval = settings.value( "/qgis/digitizing/mouseflow_t_interval",10).toInt();
  }
}

QgsMapToolCapture::~QgsMapToolCapture()
{
  delete mSnappingMarker;

  stopCapturing();

  if ( mValidator )
  {
    mValidator->deleteLater();
    mValidator = nullptr;
  }
}

void QgsMapToolCapture::activate()
{
  if ( mTempRubberBand )
    mTempRubberBand->show();

  QgsMapToolAdvancedDigitizing::activate();
}

void QgsMapToolCapture::deactivate()
{
  if ( mTempRubberBand )
    mTempRubberBand->hide();

  delete mSnappingMarker;
  mSnappingMarker = nullptr;

  QgsMapToolAdvancedDigitizing::deactivate();
}

void QgsMapToolCapture::validationFinished()
{
  emit messageDiscarded();
#ifdef TRANSLATE
  QString msgFinished = tr( "Validation finished" );
#else
  QString msgFinished = tr( "有效性检验完成" );
#endif
  if ( !mValidationWarnings.isEmpty() )
  {
    emit messageEmitted( mValidationWarnings.join( "\n" ).append( "\n" ).append( msgFinished ), IC_QgsMessageBar::WARNING );
  }
}

void QgsMapToolCapture::currentLayerChanged( QgsMapLayer *layer )
{
  if ( !mCaptureModeFromLayer )
    return;

  mCaptureMode = CaptureNone;

  QgsVectorLayer *vlayer = qobject_cast<QgsVectorLayer *>( layer );
  if ( !vlayer )
  {
    return;
  }

  switch ( vlayer->geometryType() )
  {
    case QGis::Point:
      mCaptureMode = CapturePoint;
      break;
    case QGis::Line:
      mCaptureMode = CaptureLine;
      break;
    case QGis::Polygon:
      mCaptureMode = CapturePolygon;
      break;
    default:
      mCaptureMode = CaptureNone;
      break;
  }
}


bool QgsMapToolCapture::tracingEnabled()
{
#ifdef ONEGIS_MAPTOOL
  QgsMapCanvasTracer* tracer = QgsMapCanvasTracer::tracerForCanvas( mCanvas );
#else
  IC_QgsMapCanvasTracer* tracer = C_Singleton::mapvisualInterface()->mapcanvasTracer();
#endif
  return tracer && tracer->actionEnableTracing() && tracer->actionEnableTracing()->isChecked();
}


QgsPoint QgsMapToolCapture::tracingStartPoint()
{
  try
  {
    QgsMapLayer* layer = mCanvas->currentLayer();
    if ( !layer )
      return QgsPoint();
    QgsPointV2 v = mCaptureCurve.endPoint();
    return toMapCoordinates( layer, QgsPoint( v.x(), v.y() ) );
  }
  catch ( QgsCsException & )
  {
    QgsDebugMsg( "transformation to layer coordinate failed" );
    return QgsPoint();
  }
}


bool QgsMapToolCapture::tracingMouseMove( IC_QgsMapMouseEvent* e )
{
  if ( !e->isSnapped() )
    return false;

  QgsPoint pt0 = tracingStartPoint();
  if ( pt0 == QgsPoint() )
    return false;

#ifdef ONEGIS_MAPTOOL
  QgsMapCanvasTracer* tracer = QgsMapCanvasTracer::tracerForCanvas( mCanvas );
#else
  IC_QgsMapCanvasTracer* tracer = C_Singleton::mapvisualInterface()->mapcanvasTracer();
#endif
  if ( !tracer )
    return false;  // this should not happen!

  mTempRubberBand->reset( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line );

#ifdef ONEGIS_MAPTOOL
  QgsTracer::PathError err;
#else
  IC_QgsMapCanvasTracer::PathError err;
#endif
  QVector<QgsPoint> points = tracer->findShortestPath( pt0, e->mapPoint(), &err );
  if ( points.isEmpty() )
  {
    tracer->reportError( err, false );
    return false;
  }

  if ( mCaptureMode == CapturePolygon )
    mTempRubberBand->addPoint( *mRubberBand->getPoint( 0, 0 ), false );

  //  update rubberband
  for ( int i = 0; i < points.count(); ++i )
    mTempRubberBand->addPoint( points.at( i ), i == points.count() - 1 );

#ifdef ONEGIS_MAPTOOL
  tracer->reportError( QgsTracer::ErrNone, false ); // clear messagebar if there was any error
#else
  tracer->reportError( IC_QgsMapCanvasTracer::ErrNone, false );
#endif
  return true;
}


bool QgsMapToolCapture::tracingAddVertex( const QgsPoint& point )
{
#ifdef ONEGIS_MAPTOOL
  QgsMapCanvasTracer* tracer = QgsMapCanvasTracer::tracerForCanvas( mCanvas );
#else
  IC_QgsMapCanvasTracer* tracer = C_Singleton::mapvisualInterface()->mapcanvasTracer();
#endif
  if ( !tracer )
    return false;  // this should not happen!

  if ( mCaptureCurve.numPoints() == 0 )
  {
    if ( !tracer->init() )
    {
#ifdef ONEGIS_MAPTOOL
      tracer->reportError( QgsTracer::ErrTooManyFeatures, true );
#else
      tracer->reportError( IC_QgsMapCanvasTracer::ErrTooManyFeatures, true );
#endif
      return false;
    }

    // only accept first point if it is snapped to the graph (to vertex or edge)
    bool res = tracer->isPointSnapped( point );
    if ( res )
    {
      QgsPointV2 layerPoint;
      nextPoint( QgsPointV2( point ), layerPoint ); // assuming the transform went fine earlier

      mRubberBand->addPoint( point );
      mCaptureCurve.addVertex( layerPoint );
    }
    return res;
  }

  QgsPoint pt0 = tracingStartPoint();
  if ( pt0 == QgsPoint() )
    return false;

#ifdef ONEGIS_MAPTOOL
  QgsTracer::PathError err;
#else
  IC_QgsMapCanvasTracer::PathError err;
#endif
  QVector<QgsPoint> points = tracer->findShortestPath( pt0, point, &err );
  if ( points.isEmpty() )
    return false; // ignore the vertex - can't find path to the end point!

  // transform points
  QgsPointSequenceV2 layerPoints;
  QgsPointV2 lp; // in layer coords
  for ( int i = 1; i < points.count(); ++i )
  {
    if ( nextPoint( QgsPointV2( points[i] ), lp ) != 0 )
      return false;
    layerPoints << lp;
  }

  for ( int i = 1; i < points.count(); ++i )
  {
    if ( points[i] == points[i-1] )
      continue; // avoid duplicate vertices if there are any
    mRubberBand->addPoint( points[i], i == points.count() - 1 );
    mCaptureCurve.addVertex( layerPoints[i-1] );
  }

#ifdef ONEGIS_MAPTOOL
  tracer->reportError( QgsTracer::ErrNone, true ); // clear messagebar if there was any error
#else
  tracer->reportError( IC_QgsMapCanvasTracer::ErrNone, true );
#endif
  return true;
}


void QgsMapToolCapture::cadCanvasMoveEvent( IC_QgsMapMouseEvent * e )
{
  QgsMapToolAdvancedDigitizing::cadCanvasMoveEvent( e );
  bool snapped = e->isSnapped();
  QgsPoint point = e->mapPoint();

  if ( !snapped )
  {
    delete mSnappingMarker;
    mSnappingMarker = nullptr;
  }
  else
  {
    if ( !mSnappingMarker )
    {
      mSnappingMarker = new QgsVertexMarker( mCanvas );
      mSnappingMarker->setIconType( QgsVertexMarker::ICON_CROSS );
      mSnappingMarker->setColor( Qt::magenta );
      mSnappingMarker->setPenWidth( 3 );
    }
    mSnappingMarker->setCenter( point );
  }



#if 0

  if ( !mTempRubberBand && mCaptureCurve.numPoints() > 0 )
  {
    mTempRubberBand = createRubberBand( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line, true );
    QgsPointV2 pt = mCaptureCurve.endPoint();
    mTempRubberBand->addPoint( QgsPoint( pt.x(), pt.y() ) );
    mTempRubberBand->addPoint( point );
  }


  if ( mCaptureMode != CapturePoint && mTempRubberBand && mCapturing )
  {
    bool hasTrace = false;
    if ( tracingEnabled() && mCaptureCurve.numPoints() != 0 )
    {
      hasTrace = tracingMouseMove( e );
    }

    if ( !hasTrace )
    {
      if ( mCaptureCurve.numPoints() > 0 &&
           (( mCaptureMode == CaptureLine && mTempRubberBand->numberOfVertices() != 2 ) ||
            ( mCaptureMode == CapturePolygon && mTempRubberBand->numberOfVertices() != 3 ) ) )
      {
        // fix temporary rubber band after tracing which may have added multiple points
        mTempRubberBand->reset( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line );
        if ( mCaptureMode == CapturePolygon )
          mTempRubberBand->addPoint( *mRubberBand->getPoint( 0, 0 ), false );
        QgsPointV2 pt = mCaptureCurve.endPoint();
        QgsPoint mapPt = toMapCoordinates( qobject_cast<QgsVectorLayer *>( mCanvas->currentLayer() ), QgsPoint( pt.x(), pt.y() ) );
        mTempRubberBand->addPoint( mapPt );
        mTempRubberBand->addPoint( point );
      }
      else
        mTempRubberBand->movePoint( point );
    }
  }
#else
   // Added by zhangyajun begin 2015/09/10
	if ( mMousePressed )
	{
		if ( QDateTime::currentMSecsSinceEpoch() - m_int64Time > m_iFlowTInterval )
        {
            m_int64Time = QDateTime::currentMSecsSinceEpoch();
            if ( mRubberBand == 0 )
            {
                addVertex( point );
                return;
            }
            int partSize = mRubberBand->size();
            if ( partSize <= 0 ) 
            {
                addVertex( point );
                return;
            }
            int pointSize = mRubberBand->partSize(partSize-1);
            if ( pointSize <= 1 )
            {
                addVertex( point );
                return;
			}

			const int pixTtolerance = m_iFlowDInterval; //pix
			QgsPoint prePoint = toCanvasCoordinates( *(mRubberBand->getPoint(partSize-1,pointSize-1)) );
			QgsPoint curPoint = toCanvasCoordinates(point);
			if ( abs(curPoint.x() - prePoint.x()) <= pixTtolerance && abs(curPoint.y() - prePoint.y()) <= pixTtolerance )
				return;

			addVertex( point );
		}
	}

	else 
	{
		// Added by linweiling at 2016.06.25 begin
		// 激活追踪的条件：
		// 1.不在拖拽状态下
		// 2.按住Ctrl键
		// 3.采集的最后一个点和当前点与将要追踪的要素距离达到容差范围
		/*
		if((e->modifiers() & Qt::ControlModifier) && mSmartTrackerCard && points().size() > 0)
		{
			QList<QgsPoint> result;
			QList<QgsPoint> sample;
			sample.append(points().last());
			sample.append(point);
			mTrackRubberBand->reset(QGis::Line);
			if(mSmartTrackerCard->track(sample, result))
			{
				result.insert(0, points().last());
				result.append(point);
				foreach(const QgsPoint& p, result)
					mTrackRubberBand->addPoint(p);
			}
		}
		else
		{
			mTrackRubberBand->reset(QGis::Line);
		}
		*/
		// Added end.

		// Added by zhangyajun end 2015/09/10
		if ( mCaptureMode != CapturePoint && mTempRubberBand && mCapturing )
		{
			mTempRubberBand->movePoint( point );
		}

        if ( !mTempRubberBand && mCaptureCurve.numPoints() > 0 )
        {
            mTempRubberBand = createRubberBand( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line, true );
            QgsPointV2 pt = mCaptureCurve.endPoint();
            mTempRubberBand->addPoint( QgsPoint( pt.x(), pt.y() ) );
            mTempRubberBand->addPoint( point );
        }


        if ( mCaptureMode != CapturePoint && mTempRubberBand && mCapturing )
        {
            bool hasTrace = false;
            if ( tracingEnabled() && mCaptureCurve.numPoints() != 0 )
            {
                hasTrace = tracingMouseMove( e );
            }

            if ( !hasTrace )
            {
                if ( mCaptureCurve.numPoints() > 0 &&
                    (( mCaptureMode == CaptureLine && mTempRubberBand->numberOfVertices() != 2 ) ||
                    ( mCaptureMode == CapturePolygon && mTempRubberBand->numberOfVertices() != 3 ) ) )
                {
                    // fix temporary rubber band after tracing which may have added multiple points
                    mTempRubberBand->reset( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line );
                    if ( mCaptureMode == CapturePolygon )
                        mTempRubberBand->addPoint( *mRubberBand->getPoint( 0, 0 ), false );
                    QgsPointV2 pt = mCaptureCurve.endPoint();
                    QgsPoint mapPt = toMapCoordinates( qobject_cast<QgsVectorLayer *>( mCanvas->currentLayer() ), QgsPoint( pt.x(), pt.y() ) );
                    mTempRubberBand->addPoint( mapPt );
                    mTempRubberBand->addPoint( point );
                }
                else
                    mTempRubberBand->movePoint( point );
            }
        }
	}
#endif

} // mouseMoveEvent

int QgsMapToolCapture::nextPoint( const QgsPoint& mapPoint, QgsPoint& layerPoint )
{
  QgsPointV2 p1( mapPoint.x(), mapPoint.y() );
  QgsPointV2 p2;
  int r = nextPoint( p1, p2 );
  layerPoint.setX( p2.x() );
  layerPoint.setY( p2.y() );
  return r;
}

int QgsMapToolCapture::nextPoint( const QgsPointV2& mapPoint, QgsPointV2& layerPoint )
{
  QgsVectorLayer *vlayer = qobject_cast<QgsVectorLayer *>( mCanvas->currentLayer() );
  if ( !vlayer )
  {
    QgsDebugMsg( "no vector layer" );
    return 1;
  }
  try
  {
    QgsPoint mapP( mapPoint.x(), mapPoint.y() );
    layerPoint = QgsPointV2( toLayerCoordinates( vlayer, mapP ) ); //transform snapped point back to layer crs
    if ( QgsWKBTypes::hasZ( QGis::fromOldWkbType( vlayer->wkbType() ) ) )
      layerPoint.addZValue( 0.0 );
    if ( QgsWKBTypes::hasM( QGis::fromOldWkbType( vlayer->wkbType() ) ) )
      layerPoint.addMValue( 0.0 );
  }
  catch ( QgsCsException &cse )
  {
    Q_UNUSED( cse );
    QgsDebugMsg( "transformation to layer coordinate failed" );
    return 2;
  }

  return 0;
}

int QgsMapToolCapture::nextPoint( QPoint p, QgsPointV2 &layerPoint, QgsPointV2 &mapPoint )
{
  mapPoint = QgsPointV2( toMapCoordinates( p ) );
  return nextPoint( mapPoint, layerPoint );
}

int QgsMapToolCapture::nextPoint( QPoint p, QgsPoint &layerPoint, QgsPoint &mapPoint )
{
  mapPoint = toMapCoordinates( p );
  Q_NOWARN_DEPRECATED_PUSH
  return nextPoint( mapPoint, layerPoint );
  Q_NOWARN_DEPRECATED_POP
}

int QgsMapToolCapture::fetchLayerPoint( QgsPointLocator::Match match , QgsPointV2 &layerPoint )
{
  QgsVectorLayer* vlayer = qobject_cast<QgsVectorLayer *>( mCanvas->currentLayer() );
  QgsVectorLayer* sourceLayer = match.layer();
  if ( match.isValid() && match.hasVertex() && sourceLayer &&
       ( sourceLayer->crs() == vlayer->crs() ) )
  {
    QgsFeature f;
    QgsFeatureRequest request;
    request.setFilterFid( match.featureId() );
    bool fetched = match.layer()->getFeatures( request ).nextFeature( f );
    if ( fetched )
    {
      QgsVertexId vId;
      if ( !f.geometry()->vertexIdFromVertexNr( match.vertexIndex(), vId ) )
        return 2;
      layerPoint = f.geometry()->geometry()->vertexAt( vId );
      return 0;
    }
    else
    {
      return 2;
    }
  }
  else
  {
    return 1;
  }
}

int QgsMapToolCapture::addVertex( const QgsPoint& point )
{
  return addVertex( point, QgsPointLocator::Match() );
}

int QgsMapToolCapture::addVertex( const QgsPoint& point, QgsPointLocator::Match match )
{
  if ( mode() == CaptureNone )
  {
    QgsDebugMsg( "invalid capture mode" );
    return 2;
  }

  int res;
  QgsPointV2 layerPoint;
  res = fetchLayerPoint( match, layerPoint );
  if ( res != 0 )
  {
    res = nextPoint( QgsPointV2( point ), layerPoint );
    if ( res != 0 )
    {
      return res;
    }
  }

  if ( !mRubberBand )
  {
    mRubberBand = createRubberBand( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line );
  }

  if ( !mTempRubberBand )
  {
    mTempRubberBand = createRubberBand( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line, true );
  }
  else
  {
    mTempRubberBand->reset( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line );
  }

  bool traceCreated = false;
  if ( tracingEnabled() )
  {
    traceCreated = tracingAddVertex( point );
  }

  if ( !traceCreated )
  {
    // ordinary digitizing
    mRubberBand->addPoint( point );
    mCaptureCurve.addVertex( layerPoint );
  }
 


  if ( mCaptureMode == CaptureLine )
  {
    mTempRubberBand->addPoint( point );
  }
  else if ( mCaptureMode == CapturePolygon )
  {
    const QgsPoint *firstPoint = mRubberBand->getPoint( 0, 0 );
    mTempRubberBand->addPoint( *firstPoint );
    mTempRubberBand->movePoint( point );
    mTempRubberBand->addPoint( point );
  }

  validateGeometry();

  return 0;
}

int QgsMapToolCapture::addCurve( QgsCurveV2* c )
{
  if ( !c )
  {
    return 1;
  }

  if ( !mRubberBand )
  {
    mRubberBand = createRubberBand( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line );
  }

  QgsLineStringV2* lineString = c->curveToLine();
  QgsPointSequenceV2 linePoints;
  lineString->points( linePoints );
  delete lineString;
  QgsPointSequenceV2::const_iterator ptIt = linePoints.constBegin();
  for ( ; ptIt != linePoints.constEnd(); ++ptIt )
  {
    mRubberBand->addPoint( QgsPoint( ptIt->x(), ptIt->y() ) );
  }

  if ( !mTempRubberBand )
  {
    mTempRubberBand = createRubberBand( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line, true );
  }
  else
  {
    mTempRubberBand->reset();
  }
  QgsPointV2 endPt = c->endPoint();
  mTempRubberBand->addPoint( QgsPoint( endPt.x(), endPt.y() ) ); //add last point of c

  //transform back to layer CRS in case map CRS and layer CRS are different
  QgsVectorLayer* vlayer = qobject_cast<QgsVectorLayer *>( mCanvas->currentLayer() );
  const QgsCoordinateTransform* ct =  mCanvas->mapSettings().layerTransform( vlayer );
  if ( ct )
  {
    c->transform( *ct, QgsCoordinateTransform::ReverseTransform );
  }
  mCaptureCurve.addCurve( c );

  return 0;
}


void QgsMapToolCapture::undo()
{
 if ( mRubberBand )
  {
    int rubberBandSize = mRubberBand->numberOfVertices();
    int tempRubberBandSize = mTempRubberBand->numberOfVertices();
    int captureListSize = size();

    if ( rubberBandSize < 1 || captureListSize < 1  )
    {
      return;
    }


    mRubberBand->removePoint( -1 );

    

    if ( rubberBandSize > 1 )
    {
      if ( tempRubberBandSize >= 1 )
      {
          const QgsPoint *point = mRubberBand->getPoint( 0, rubberBandSize - 2 );
          mTempRubberBand->movePoint( tempRubberBandSize - 2, *point );
          
      }
      
    }
    else
    {
      mTempRubberBand->reset( mCaptureMode == CapturePolygon ? QGis::Polygon : QGis::Line );
    }

    QgsVertexId vertexToRemove;
    vertexToRemove.part = 0;
    vertexToRemove.ring = 0;
    vertexToRemove.vertex = size() - 1;

    mCaptureCurve.deleteVertex( vertexToRemove );
    
    //mCaptureCurve.deleteVertex( vertexToRemove );
    validateGeometry();
  }
}

void QgsMapToolCapture::keyPressEvent( QKeyEvent* e )
{
  if ( e->key() == Qt::Key_Backspace || e->key() == Qt::Key_Delete )
  {
   undo();

    // Override default shortcut management in MapCanvas
    e->ignore();
  }
  else if ( e->key() == Qt::Key_Escape )
  {
    stopCapturing();

    // Override default shortcut management in MapCanvas
    e->ignore();
  }
}

void QgsMapToolCapture::startCapturing()
{
  mCapturing = true;
}

bool QgsMapToolCapture::isCapturing() const
{
  return mCapturing;
}

void QgsMapToolCapture::stopCapturing()
{
  if ( mRubberBand )
  {
    delete mRubberBand;
    mRubberBand = nullptr;
  }

  if ( mTempRubberBand )
  {
    delete mTempRubberBand;
    mTempRubberBand = nullptr;
  }

  while ( !mGeomErrorMarkers.isEmpty() )
  {
    delete mGeomErrorMarkers.takeFirst();
  }

  mGeomErrors.clear();

#ifdef Q_OS_WIN
  Q_FOREACH ( QWidget *w, qApp->topLevelWidgets() )
  {
    if ( w->objectName() == "QgisApp" )
    {
      if ( mSkipNextContextMenuEvent++ == 0 )
        w->installEventFilter( this );
      break;
    }
  }
#endif

  mCapturing = false;
  mCaptureCurve.clear();
  if ( currentVectorLayer() )
    currentVectorLayer()->triggerRepaint();
}

void QgsMapToolCapture::deleteTempRubberBand()
{
  if ( mTempRubberBand )
  {
    delete mTempRubberBand;
    mTempRubberBand = nullptr;
  }
}

void QgsMapToolCapture::closePolygon()
{
  mCaptureCurve.close();
}

void QgsMapToolCapture::validateGeometry()
{
  QSettings settings;
  if ( settings.value( "/qgis/digitizing/validate_geometries", 1 ).toInt() == 0 )
    return;

  if ( mValidator )
  {
    mValidator->deleteLater();
    mValidator = nullptr;
  }

  mValidationWarnings.clear();
  mGeomErrors.clear();
  while ( !mGeomErrorMarkers.isEmpty() )
  {
    delete mGeomErrorMarkers.takeFirst();
  }

  QScopedPointer<QgsGeometry> g;

  switch ( mCaptureMode )
  {
    case CaptureNone:
    case CapturePoint:
      return;
    case CaptureLine:
      if ( size() < 2 )
        return;
      g.reset( new QgsGeometry( mCaptureCurve.curveToLine() ) );
      break;
    case CapturePolygon:
      if ( size() < 3 )
        return;
      QgsLineStringV2* exteriorRing = mCaptureCurve.curveToLine();
      exteriorRing->close();
      QgsPolygonV2* polygon = new QgsPolygonV2();
      polygon->setExteriorRing( exteriorRing );
      g.reset( new QgsGeometry( polygon ) );
      break;
  }

  if ( !g.data() )
    return;

  mValidator = new QgsGeometryValidator( g.data() );
  connect( mValidator, SIGNAL( errorFound( QgsGeometry::Error ) ), this, SLOT( addError( QgsGeometry::Error ) ) );
  connect( mValidator, SIGNAL( finished() ), this, SLOT( validationFinished() ) );
  mValidator->start();
#ifdef TRANSLATE
  messageEmitted( tr( "Validation started" ) );
#else
  messageEmitted( tr( "有效性检验开始" ) );
#endif
}

void QgsMapToolCapture::addError( QgsGeometry::Error e )
{
  mGeomErrors << e;
  QgsVectorLayer *vlayer = qobject_cast<QgsVectorLayer *>( mCanvas->currentLayer() );
  if ( !vlayer )
    return;

  mValidationWarnings << e.what();

  if ( e.hasWhere() )
  {
    QgsVertexMarker *vm =  new QgsVertexMarker( mCanvas );
    vm->setCenter( mCanvas->mapSettings().layerToMapCoordinates( vlayer, e.where() ) );
    vm->setIconType( QgsVertexMarker::ICON_X );
    vm->setPenWidth( 2 );
    vm->setToolTip( e.what() );
    vm->setColor( Qt::green );
    vm->setZValue( vm->zValue() + 1 );
    mGeomErrorMarkers << vm;
  }

  emit messageDiscarded();
  emit messageEmitted( mValidationWarnings.join( "\n" ), IC_QgsMessageBar::WARNING );
}

int QgsMapToolCapture::size()
{
  return mCaptureCurve.numPoints();
}

QList<QgsPoint> QgsMapToolCapture::points()
{
    //add by lipo 20180508
    if ( mRubberBand->numberOfVertices() > size())
    {
        
        QgsPointV2 layerPoint;
        const QgsPoint* point = mRubberBand->getPoint(0,0);
        nextPoint( QgsPointV2( *point ), layerPoint ); // assuming the transform went fine earlier

        QgsVertexId vertexToAdd;
        vertexToAdd.part = 0;
        vertexToAdd.ring = 0;
        vertexToAdd.vertex = 0;
        mCaptureCurve.insertVertex(vertexToAdd, layerPoint);
       
    }
     //end
    

  QgsPointSequenceV2 pts;
  QList<QgsPoint> points;
  mCaptureCurve.points( pts );
  QgsGeometry::convertPointList( pts, points );
  return points;
}

void QgsMapToolCapture::setPoints( const QList<QgsPoint>& pointList )
{
  QgsPointSequenceV2 pts;
  QgsGeometry::convertPointList( pointList, pts );

  QgsLineStringV2* line = new QgsLineStringV2();
  line->setPoints( pts );

  mCaptureCurve.clear();
  mCaptureCurve.addCurve( line );
}

bool QgsMapToolCapture::isValid()
{
    if (  mValidator != nullptr && mValidator->getErrorCount() >= 1 )
    {
        C_Singleton::mapvisualInterface()->messageBar()->pushMessage(
            QObject::tr( "警告" ),
            QObject::tr( "要素不合法,不能添加" ),
            IC_QgsMessageBar::WARNING);
        return false;
    }

    if ( mRubberBand->asGeometry()->isGeosValid() )
    {
        return true;
    }
    return false;
}

#ifdef Q_OS_WIN
bool QgsMapToolCapture::eventFilter( QObject *obj, QEvent *event )
{
  if ( event->type() != QEvent::ContextMenu )
    return false;

  if ( --mSkipNextContextMenuEvent == 0 )
    obj->removeEventFilter( this );

  return mSkipNextContextMenuEvent >= 0;
}
#endif
