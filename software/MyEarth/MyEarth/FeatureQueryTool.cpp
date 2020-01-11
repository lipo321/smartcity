#include "FeatureQueryTool.h"
#include <QString>


FeatureQueryTool::FeatureQueryTool(void)
{
}


FeatureQueryTool::~FeatureQueryTool(void)
{
}


bool FeatureQueryTool::handle(const osgGA::GUIEventAdapter& ea,osgGA::GUIActionAdapter& aa)
{

    osg::ref_ptr<osgViewer::View> view = dynamic_cast<osgViewer::View*>(&aa);
    if (!view) {
        return false;
    }
    osgGA::GUIEventAdapter::EventType eventType = ea.getEventType();
    switch (eventType) {
    case osgGA::GUIEventAdapter::PUSH://鼠标按下
        if (ea.getButton() == osgGA::GUIEventAdapter::LEFT_MOUSE_BUTTON) {
           // pick(view,ea);
        }
        break;
    case osgGA::GUIEventAdapter::DRAG://鼠标拖动

        break;
    case osgGA::GUIEventAdapter::RELEASE://鼠标松开
        if (ea.getButton() == osgGA::GUIEventAdapter::LEFT_MOUSE_BUTTON) {

        }
        break;
    default:
        break;
    }

    return false;
}

void FeatureQueryTool::pick(osgViewer::View* view,const osgGA::GUIEventAdapter& ea)
{

    osgUtil::LineSegmentIntersector::Intersections intersections;
    if (view->computeIntersections(ea, intersections)) {
        osgUtil::LineSegmentIntersector::Intersections::iterator inter = intersections.begin();//获取第一个点
        
        const osg::NodePath& nodePath = inter->nodePath;//获取第一个交点的Path
        for (int i = 1; i < nodePath.size() - 1; i++) {
            std::string name = nodePath[i]->getName();
            QString mm = QString::fromStdString(name);
        }
    }
}