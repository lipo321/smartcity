#include "OSGEarth.h"
#include <QFileDialog>
#include <QLabel>
OSGEarth::OSGEarth(QWidget *parent)
	: QMainWindow(parent)
{
	ui.setupUi(this);
	setWindowTitle(QString::fromLocal8Bit("������ʱ�ն�����άƽ̨"));

	setWindowIcon( QIcon( ":/OSGEarth/images/OneGIS3D.png" ) );
	m_pDrawLine = NULL;
	m_pPolygon = NULL;
	m_pCircle = NULL;
	m_pDistance = NULL;
	m_pArea = NULL;
	m_pHeight = NULL;
	m_pWater = NULL;
	m_pLineAns = NULL;
	m_pAreaAns = NULL;
	m_pAvatarNode = NULL;
	m_pImage = NULL;
	m_pGraticule = NULL;
	m_pRadarMap = nullptr;

	m_pViewer = new GraphicsView(QString::fromUtf8("D:/earth/OsgEarth2.8_SDK/tests/feature_geom.earth"), this);
	this->setCentralWidget(m_pViewer);

	m_pDockWidget = new QDockWidget(this);
	m_pDockWidget->setWindowTitle(QString::fromLocal8Bit("���Ա�"));
	m_pDockWidget->setAllowedAreas( Qt::AllDockWidgetAreas);
	addDockWidget(Qt::RightDockWidgetArea, m_pDockWidget);
	m_pDockWidget->show();

	m_pTreeDockWidget = new QDockWidget(this);
	m_pTreeDockWidget->setWindowTitle(QString::fromLocal8Bit("�����ܹ�"));
	m_pDockWidget->setAllowedAreas( Qt::AllDockWidgetAreas);
	addDockWidget(Qt::LeftDockWidgetArea, m_pTreeDockWidget);
	m_pTreeDockWidget->show();

	connect(ui.actionLine, SIGNAL(triggered()), this, SLOT(slotLine()));
	connect(ui.actionPolygon, SIGNAL(triggered()), this, SLOT(slotPolygon()));
	connect(ui.actionCircle, SIGNAL(triggered()), this, SLOT(slotCircle()));
	connect(ui.actionDistance, SIGNAL(triggered()), this, SLOT(slotDistance()));
	connect(ui.actionArea, SIGNAL(triggered()), this, SLOT(slotArea()));
	connect(ui.actionHeight, SIGNAL(triggered()), this, SLOT(slotHeight()));
	connect(ui.actionWater, SIGNAL(triggered()), this, SLOT(slotWater()));
	connect(ui.actionLineAns, SIGNAL(triggered()), this, SLOT(slotLineIntervisibility()));
	connect(ui.actionAreaAns, SIGNAL(triggered()), this, SLOT(slotAreaIntervisibility()));
	connect(ui.actionAvatar, SIGNAL(triggered()), this, SLOT(slotAvatar()));
	connect(ui.actionOverlay, SIGNAL(triggered()), this, SLOT(slotOverlay()));
	connect(ui.actionGride, SIGNAL(triggered()), this, SLOT(slotGride()));
	connect(ui.actionMap, SIGNAL(triggered()), this, SLOT(slotRadarMap()));
	connect(ui.actionSwitchMap, SIGNAL(triggered()), this, SLOT(slotSwitchMap()));
    connect(ui.actionCatalog,SIGNAL(triggered()), this, SLOT(slotSetCatalog()));

	
}

OSGEarth::~OSGEarth()
{
	delete m_pDockWidget;
}

void OSGEarth::slotSetCatalog()
{
    QLabel* label = new QLabel();
    label->setText(QString::fromLocal8Bit("��ʾ���ж�������"));
    m_pDockWidget->setWidget(label);
}

void OSGEarth::slotLine()
{
	if (ui.actionLine->isChecked())
	{
		if (m_pDrawLine == NULL)
		{
			m_pDrawLine = new DrawLine(m_pViewer);
		}
		m_pDrawLine->benginHandle();
	}
	else
	{
		if (m_pDrawLine)
		{
			m_pDrawLine->endHandle();
		}
	}
}

void OSGEarth::slotPolygon()
{
	if (ui.actionPolygon->isChecked())
	{
		if (m_pPolygon == NULL)
		{
			m_pPolygon = new DrawPolygon(m_pViewer);
		}

		m_pPolygon->benginHandle();
	}
	else
	{
		if (m_pPolygon != NULL)
		{
			m_pPolygon->endHandle();
		}
	}
}

void OSGEarth::slotCircle()
{
	if (ui.actionCircle->isChecked())
	{
		if (m_pCircle == NULL)
		{
			m_pCircle = new DrawCircle(m_pViewer);
		}

		m_pCircle->benginHandle();
	}
	else
	{
		if (m_pCircle != NULL)
		{
			m_pCircle->endHandle();
		}
	}
}

void OSGEarth::slotDistance()
{
	if (ui.actionDistance->isChecked())
	{
		if (m_pDistance == NULL)
		{
			m_pDistance = new Distance(m_pViewer);
		}

		m_pDistance->benginHandle();
	}
	else
	{
		if (m_pDistance != NULL)
		{
			m_pDistance->endHandle();
		}
	}
}

void OSGEarth::slotArea()
{
	if (ui.actionArea->isChecked())
	{
		if (m_pArea == NULL)
		{
			m_pArea = new Area(m_pViewer);
		}
		m_pArea->benginHandle();
	}
	else
	{
		m_pArea->endHandle();
	}
}

void OSGEarth::slotHeight()
{
	if (ui.actionHeight->isChecked())
	{
		if (m_pHeight == NULL)
		{
			m_pHeight = new Height(m_pViewer);
		}
		m_pHeight->benginHandle();
	}
	else
	{
		m_pHeight->endHandle();
	}
}

void OSGEarth::slotWater()
{
	if (ui.actionWater->isChecked())
	{
		if (m_pWater == NULL)
		{
			m_pWater = new Water(m_pViewer);
		}
		m_pWater->benginHandle();
	}
	else
	{
		m_pWater->endHandle();
	}
}

void OSGEarth::slotLineIntervisibility()
{
	if (ui.actionLineAns->isChecked())
	{
		if (m_pLineAns == NULL)
		{
			m_pLineAns = new LineAnalysis(m_pViewer);
		}
		m_pLineAns->benginHandle();
	}
	else
	{
		m_pLineAns->endHandle();
	}
}

void OSGEarth::slotAreaIntervisibility()
{
	if (ui.actionAreaAns->isChecked())
	{
		if (m_pAreaAns == NULL)
		{
			m_pAreaAns = new AreaAnalysis(m_pViewer);
		}
		m_pAreaAns->benginHandle();
	}
	else
	{
		if (m_pAreaAns)
		{
			m_pAreaAns->endHandle();
		}
	}
}

void OSGEarth::slotAvatar()
{
	if (ui.actionAvatar->isChecked())
	{
		if (m_pAvatarNode == NULL)
		{
			m_pAvatarNode = new Avatar(m_pViewer);
		}
		m_pAvatarNode->benginHandle();
	}
	else
	{
		m_pAvatarNode->endHandle();
	}
}

void OSGEarth::slotOverlay()
{
	if (ui.actionOverlay->isChecked())
	{
		if (m_pImage == NULL)
		{
			m_pImage = new osgEarth::Annotation::ImageOverlay(m_pViewer->getMapNode());
			m_pImage->setImage(osgDB::readImageFile("E:/project/OSGSrc/src/osgearth-2.6.0/data/tree.gif"));
			m_pImage->setBounds(osgEarth::Bounds::Bounds(106.88, 22.5, 101.25, 28.125));
			m_pViewer->getRoot()->addChild(m_pImage);
		}
	}
	else
	{
		if (m_pImage)
		{
			m_pViewer->getRoot()->removeChild(m_pImage);
			m_pImage = NULL;
		}
	}
}

void OSGEarth::slotGride()
{
	if (ui.actionGride->isChecked())
	{
		if (m_pGraticule == nullptr)
		{
			m_pGraticule = new osgEarth::Util::GeodeticGraticule(m_pViewer->getMapNode());
			// ��ɫ����Ϊ��ɫ
			osgEarth::Util::GeodeticGraticuleOptions op = m_pGraticule->getOptions();
			op.lineStyle()->getOrCreate<osgEarth::Util::LineSymbol>()
				->stroke()->color().set(1, 0, 0, 1);
			m_pGraticule->setOptions(op);
			m_pViewer->getRoot()->addChild(m_pGraticule);
		}
	}
	else
	{
		if (m_pGraticule)
		{
			m_pViewer->getRoot()->removeChild(m_pGraticule);
			m_pGraticule = nullptr;
		}
	}
}

void OSGEarth::slotRadarMap()
{
	if (ui.actionMap->isChecked())
	{
		if (m_pRadarMap == nullptr)
		{
			m_pRadarMap = new RadarMap(m_pViewer);
		}
		m_pRadarMap->enableMap();
	}
	else
	{
		if (m_pRadarMap)
		{
			m_pRadarMap->disableMap();
		}
	}
}

void OSGEarth::slotSwitchMap()
{
	QString strEarthPath = QFileDialog::getOpenFileName(this,
		QString("open map"),
		"../earthFiles",
		"earth (*.earth)");
   
    m_pViewer->init();
	QApplication::setOverrideCursor(Qt::WaitCursor);
	m_pViewer->setEarthFile(strEarthPath);
	QApplication::setOverrideCursor(Qt::ArrowCursor);
}
