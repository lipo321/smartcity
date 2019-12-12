/***************************************************************************
    qgsmaptooladvanceddigitizing.cpp  - map tool with event in map coordinates
    ----------------------
    begin                : October 2014
    copyright            : (C) Denis Rouzaud
    email                : denis.rouzaud@gmail.com
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
#ifdef ONEGIS_MAPTOOL
#include "qgsmapmouseevent.h"
#else
#include "IC_QgsMapMouseEvent.h"
#include "C_Singleton.h"
#include "IC_QgsMapCanvas.h"
#endif
#include "qgsmaptooladvanceddigitizing.h"


QgsMapToolAdvancedDigitizing::QgsMapToolAdvancedDigitizing( IC_QgsMapCanvas* canvas, QgsAdvancedDigitizingDockWidget* cadDockWidget )
    : QgsMapToolEdit( canvas )
    , mCaptureMode( CapturePoint )
    , mSnapOnPress( false )
    , mSnapOnRelease( false )
    , mSnapOnMove( false )
    , mSnapOnDoubleClick( false )
    , mCadDockWidget( cadDockWidget )
    , mMousePressed(false)
{
}

QgsMapToolAdvancedDigitizing::~QgsMapToolAdvancedDigitizing()
{
}

void QgsMapToolAdvancedDigitizing::canvasPressEvent( IC_QgsMapMouseEvent* e )
{
    //added by lipo 20180308
    IC_QgsMapMouseEvent::SnappingMode mode = mSnapOnPress ? IC_QgsMapMouseEvent::SnapProjectConfig : IC_QgsMapMouseEvent::NoSnapping;
    if( e->button() == Qt::LeftButton )
        mMousePressed = true;
    //end
  snap( e );
#ifdef ONEGIS_MAPTOOL
  if ( !mCadDockWidget->canvasPressEvent( e ) )
    cadCanvasPressEvent( e );
#else
  cadCanvasPressEvent( e );
#endif
}

void QgsMapToolAdvancedDigitizing::canvasReleaseEvent( IC_QgsMapMouseEvent* e )
{
    //added by lipo 20180308
    mMousePressed = false;
    //end
  snap( e );


#ifdef ONEGIS_MAPTOOL
  if ( !mCadDockWidget->canvasReleaseEvent( e, mCaptureMode == CaptureLine || mCaptureMode == CapturePolygon ) )
    cadCanvasReleaseEvent( e );
#else
  cadCanvasReleaseEvent( e );
#endif
}

void QgsMapToolAdvancedDigitizing::canvasMoveEvent( IC_QgsMapMouseEvent* e )
{
  snap( e );
#ifdef ONEGIS_MAPTOOL
  if ( !mCadDockWidget->canvasMoveEvent( e ) )
    cadCanvasMoveEvent( e );
#else
  cadCanvasMoveEvent( e );
#endif
}

void QgsMapToolAdvancedDigitizing::activate()
{
  QgsMapToolEdit::activate();
#ifdef ONEGIS_MAPTOOL
  connect( mCadDockWidget, SIGNAL( pointChanged( QgsPoint ) ), this, SLOT( cadPointChanged( QgsPoint ) ) );
  mCadDockWidget->enable();
#endif
}

void QgsMapToolAdvancedDigitizing::deactivate()
{
  QgsMapToolEdit::deactivate();
#ifdef ONEGIS_MAPTOOL
  disconnect( mCadDockWidget, SIGNAL( pointChanged( QgsPoint ) ), this, SLOT( cadPointChanged( QgsPoint ) ) );
  mCadDockWidget->disable();
#endif
}

void QgsMapToolAdvancedDigitizing::cadPointChanged( const QgsPoint& point )
{
#ifdef ONEGIS_MAPTOOL
  QgsMapMouseEvent fakeEvent( mCanvas, QMouseEvent::Move, QPoint( 0, 0 ) );
  fakeEvent.setMapPoint( point );
  canvasMoveEvent( &fakeEvent );
#else
    /*IC_2DMapVisualInterfacePtr p = C_Singleton::mapvisualInterface();
    if ( p )
    {
        IC_QgsMapMouseEvent *fakeEvent = p->mapCanvas()->createMapMouseEvent( QMouseEvent::Move, QPoint( 0, 0 ) );
        fakeEvent->setMapPoint( point );
        canvasMoveEvent( fakeEvent );
        delete fakeEvent;
    }*/
#endif
}

void QgsMapToolAdvancedDigitizing::snap( IC_QgsMapMouseEvent* e )
{
#ifdef ONEGIS_MAPTOOL
  if ( !mCadDockWidget->cadEnabled() )
    e->snapPoint( IC_QgsMapMouseEvent::SnapProjectConfig );
#else
    e->snapPoint( IC_QgsMapMouseEvent::SnapProjectConfig );
#endif
}
