#ifndef FEATUREQUERYTOOL_H
#define FEATUREQUERYTOOL_H

#include <osgGA/GUIEventHandler>
#include <osgGA/GUIActionAdapter>
#include <osgViewer/View>

typedef std::vector< osg::observer_ptr<osg::Callback> > Callbacks;

class FeatureQueryTool :public osgGA::GUIEventHandler
{
public:
    FeatureQueryTool(void);
    virtual ~FeatureQueryTool(void);

private:
    virtual bool handle(const osgGA::GUIEventAdapter& ea,osgGA::GUIActionAdapter& aa) override;
    void pick(osgViewer::View* view,const osgGA::GUIEventAdapter& ea);
private:
    Callbacks _callbacks;
};
#endif
