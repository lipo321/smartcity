/***************************************************************************
    qgsmaptool.cpp  -  base class for map canvas tools
    ----------------------
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

#include "qgslogger.h"
#include "qgsmaptool.h"
#if 0
#include "qgsmapcanvas.h"
#else
#include "IC_QgsMapCanvas.h"
#include "QSettings.h"
#include <QWheelEvent>
#endif
#include "qgsmaptopixel.h"
#include "qgsmaprenderer.h"
#include "qgsrendercontext.h"
#include <QAction>
#include <QAbstractButton>

#if QGSMAPCANVAS
QgsMapTool::QgsMapTool( QgsMapCanvas* canvas )
    : QObject( canvas )
    , mCanvas( canvas )
    , mCursor( Qt::CrossCursor )
    , mAction( nullptr )
    , mButton( nullptr )
    , mToolName( QString() )
{
}
#else
QgsMapTool::QgsMapTool( IC_QgsMapCanvas* canvas )
    : IC_QgsMapTool(  )
    , mCanvas( canvas )
    , mCursor( Qt::CrossCursor )
    , mAction( nullptr )
    , mButton( nullptr )
    , mToolName( QString() )
{
}
#endif


QgsMapTool::~QgsMapTool()
{
  mCanvas->unsetMapTool( this );
}


QgsPoint QgsMapTool::toMapCoordinates( QPoint point )
{
  return mCanvas->getCoordinateTransform()->toMapCoordinates( point );
}

QgsPointV2 QgsMapTool::toMapCoordinates( QgsMapLayer* layer, const QgsPointV2& point )
{
  QgsPoint result = mCanvas->mapSettings().layerToMapCoordinates( layer, QgsPoint( point.x(), point.y() ) );
  return QgsPointV2( result.x(), result.y() );
}


QgsPoint QgsMapTool::toLayerCoordinates( QgsMapLayer* layer, QPoint point )
{
  QgsPoint pt = toMapCoordinates( point );
  return toLayerCoordinates( layer, pt );
}

QgsPoint QgsMapTool::toLayerCoordinates( QgsMapLayer* layer, const QgsPoint& point )
{
  return mCanvas->mapSettings().mapToLayerCoordinates( layer, point );
}

QgsPoint QgsMapTool::toMapCoordinates( QgsMapLayer* layer, const QgsPoint& point )
{
  return mCanvas->mapSettings().layerToMapCoordinates( layer, point );
}

QgsRectangle QgsMapTool::toLayerCoordinates( QgsMapLayer* layer, const QgsRectangle& rect )
{
  return mCanvas->mapSettings().mapToLayerCoordinates( layer, rect );
}

QPoint QgsMapTool::toCanvasCoordinates( const QgsPoint& point )
{
  qreal x = point.x(), y = point.y();
  mCanvas->getCoordinateTransform()->transformInPlace( x, y );
  return QPoint( qRound( x ), qRound( y ) );
}


void QgsMapTool::activate()
{
  // make action and/or button active
  if ( mAction )
    mAction->setChecked( true );
  if ( mButton )
    mButton->setChecked( true );

  // set cursor (map tools usually set it in constructor)
  mCanvas->setCursor( mCursor );
  QgsDebugMsg( "Cursor has been set" );

  emit activated();
}


void QgsMapTool::deactivate()
{
  if ( mAction )
    mAction->setChecked( false );
  if ( mButton )
    mButton->setChecked( false );

  emit deactivated();
}

void QgsMapTool::setAction( QAction* action )
{
  if ( mAction )
    disconnect( mAction, SIGNAL( destroyed() ), this, SLOT( actionDestroyed() ) );
  mAction = action;
  if ( mAction )
    connect( mAction, SIGNAL( destroyed() ), this, SLOT( actionDestroyed() ) );
}

void QgsMapTool::actionDestroyed()
{
  if ( mAction == sender() )
    mAction = nullptr;
}

QAction* QgsMapTool::action()
{
  return mAction;
}

void QgsMapTool::setButton( QAbstractButton* button )
{
  mButton = button;
}

QAbstractButton* QgsMapTool::button()
{
  return mButton;
}

void QgsMapTool::setCursor( const QCursor& cursor )
{
  mCursor = cursor;
}


void QgsMapTool::canvasMoveEvent( IC_QgsMapMouseEvent* e )
{
  Q_UNUSED( e );
}

void QgsMapTool::canvasDoubleClickEvent( IC_QgsMapMouseEvent* e )
{
  Q_UNUSED( e );
}

void QgsMapTool::canvasPressEvent( IC_QgsMapMouseEvent* e )
{
  Q_UNUSED( e );
}

void QgsMapTool::canvasReleaseEvent( IC_QgsMapMouseEvent* e )
{
  Q_UNUSED( e );
}

void QgsMapTool::wheelEvent( QWheelEvent *e )
{
  e->ignore();
}

void QgsMapTool::keyPressEvent( QKeyEvent *e )
{
  Q_UNUSED( e );
}

void QgsMapTool::keyReleaseEvent( QKeyEvent *e )
{
  Q_UNUSED( e );
}

#ifdef HAVE_TOUCH
bool QgsMapTool::gestureEvent( QGestureEvent *e )
{
  Q_UNUSED( e );
  return true;
}
#endif

void QgsMapTool::renderComplete()
{
}

bool QgsMapTool::isTransient() const
{
  return flags() & Transient;
}

bool QgsMapTool::isEditTool() const
{
  return flags() & EditTool;
}
#if QGSMAPCANVAS
QgsMapCanvas* QgsMapTool::canvas()
{
  return mCanvas;
}
#else
IC_QgsMapCanvas* QgsMapTool::canvas()
{
	return mCanvas;
}
#endif


double QgsMapTool::searchRadiusMM()
{
  QSettings settings;
  double radius = settings.value( "/Map/searchRadiusMM", QGis::DEFAULT_SEARCH_RADIUS_MM ).toDouble();

  if ( radius > 0 )
  {
    return radius;
  }
  return QGis::DEFAULT_SEARCH_RADIUS_MM;
}

double QgsMapTool::searchRadiusMU( const QgsRenderContext& context )
{
  return searchRadiusMM() * context.scaleFactor() * context.mapToPixel().mapUnitsPerPixel();
}
#if QGSMAPCANVAS
double QgsMapTool::searchRadiusMU( QgsMapCanvas * canvas )
#else
double QgsMapTool::searchRadiusMU( IC_QgsMapCanvas * canvas )
#endif
{
  if ( !canvas )
  {
    return 0;
  }
  QgsMapSettings mapSettings = canvas->mapSettings();
  QgsRenderContext context = QgsRenderContext::fromMapSettings( mapSettings );
  return searchRadiusMU( context );
}
