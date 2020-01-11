#include "GxWorld.h"

struct GxWorld::Hidden
{
public:
	Hidden(HWND hWnd, GxWorld* world)
	{
		m_hWnd = hWnd;
		InitOSG(world);
	}

	~Hidden()
	{

	}

	static void Render(void* ptr)
	{
		Hidden* osg = (Hidden*)ptr;
		
		osgViewer::Viewer* viewer = osg->getViewer();
		viewer->setDone(false);
		viewer->setDataVariance(osg::Object::DYNAMIC);

		while(!viewer->done())
		{
			viewer->frame();
		}
	}

	osg::ref_ptr<osgViewer::Viewer> getViewer()
	{
		return mViewer;
	}

	osg::ref_ptr<osgEarth::MapNode> getMapMode()
	{
		return mMapNode;
	}

	osg::ref_ptr<osg::Group> getRoot()
	{
		return mRoot;
	}

	osg::ref_ptr<osg::Group> getAnnoGroup()
	{
		return mAnnoGroup;
	}

	osg::ref_ptr<osg::Group> getEditGroup()
	{
		return mEditGroup;
	}

private:
	void InitOSG(GxWorld* world)
	{
		mRoot = new osg::Group();
		mMap = new osgEarth::Map();

		osgEarth::Drivers::TMSOptions imagery;
		imagery.url() = "E:/osg/srtmdata/base_image/tms.xml";
		mMap->addImageLayer(new osgEarth::ImageLayer("Imagery", imagery));

		osgEarth::Drivers::TMSOptions elevation;
		elevation.url() = "E:/osg/srtmdata/dem/tms.xml";
		mMap->addElevationLayer(new osgEarth::ElevationLayer("Elevation", elevation));

		mMapNode = new osgEarth::MapNode(mMap.get());

		
		
		mAnnoGroup = new osg::Group();
		mEditGroup = new osg::Group();

		mRoot->addChild(mMapNode.get());
		mRoot->addChild(mAnnoGroup.get());
		mRoot->addChild(mEditGroup.get());

		osgEarth::Util::SkyNode* sky = osgEarth::Util::SkyNode::create(mMapNode);
		sky->setMoonVisible(false);
		sky->setSunVisible(true);
		sky->setLighting(osg::StateAttribute::OFF);
		sky->setMinimumAmbient(osg::Vec4d(0.0f, 0.f, 0.f, 0.f));
		mRoot->addChild(sky);

		RECT rect;
		mViewer = new osgViewer::Viewer();

		mViewer->addEventHandler(new osgViewer::StatsHandler);
		::GetWindowRect(m_hWnd, &rect);
		osg::ref_ptr<osg::GraphicsContext::Traits> traits = new osg::GraphicsContext::Traits;
		osg::ref_ptr<osg::Referenced> windata = new osgViewer::GraphicsWindowWin32::WindowData(m_hWnd);

		traits->x = 0;
		traits->y = 0;
		traits->width = rect.right - rect.left;
		traits->height = rect.bottom - rect.top;
		traits->windowDecoration = false;
		traits->doubleBuffer = true;
		traits->sharedContext = 0;
		traits->setInheritedWindowPixelFormat = true;
		traits->inheritedWindowData = windata;

		osg::GraphicsContext* gc = osg::GraphicsContext::createGraphicsContext(traits.get());
		osg::ref_ptr<osg::Camera> camera = mViewer->getCamera();
		camera->setGraphicsContext(gc);
		camera->setViewport(new osg::Viewport(0, 0, traits->width, traits->height));
		camera->setClearMask(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
		camera->setClearColor(osg::Vec4f(0.0f, 0.0f, 0.0f, 1.0f));
		camera->setProjectionMatrixAsPerspective(
			30.0f, static_cast<double>(traits->width)/static_cast<double>(traits->height), 1.0, 1000.0);
		
		mViewer->setCamera(camera.get());
		mViewer->setCameraManipulator(new osgEarth::Util::EarthManipulator);
		mViewer->setSceneData(mRoot.get());
		osgEarth::Viewpoint viewpoint = ((osgEarth::Util::EarthManipulator*)(mViewer->getCameraManipulator()))->getViewpoint();
		const osgEarth::Viewpoint pos = osgEarth::Viewpoint("MyCenter", 111.625, 22.5833, 0.0, viewpoint.getHeading(), viewpoint.getPitch(), viewpoint.getRange());
		((osgEarth::Util::EarthManipulator*)(mViewer->getCameraManipulator()))->setViewpoint(pos, 2.0);
		((osgEarth::Util::EarthManipulator*)(mViewer->getCameraManipulator()))->setHomeViewpoint(pos);

		mViewer->realize();
	}

private:
	HWND m_hWnd;

	osg::ref_ptr<osgViewer::Viewer> mViewer;
	osg::ref_ptr<osg::Group> mRoot;
	osg::ref_ptr<osg::Group> mAnnoGroup;
	osg::ref_ptr<osg::Group> mEditGroup;
	osg::ref_ptr<osgEarth::Map> mMap;
	osg::ref_ptr<osgEarth::MapNode> mMapNode;

	GVAnnotations* anno;
};

GxWorld::GxWorld(HWND hwnd)
{
	h = new Hidden(hwnd, this);
}

GxWorld::~GxWorld()
{

}

void GxWorld::Display()
{
	h->getViewer()->setDone(true);
	HANDLE mThreadHandle = (HANDLE)_beginthread(&Hidden::Render, 0, h);
}

void GxWorld::InitAnno()
{
	anno = new GVAnnotations(h->getViewer(), h->getRoot(), h->getMapMode(), h->getAnnoGroup(), h->getEditGroup());
}

void GxWorld::sendKey(int key)
{
	anno->initGeom(key);
}

I_GxWorld* GxWorld_CreateWorldObject(HWND hwnd)
{
	return new GxWorld(hwnd);
}