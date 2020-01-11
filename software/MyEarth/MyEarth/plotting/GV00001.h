#ifndef __GV00001_H__
#define __GV00001_H__

#include "GVGeometry.h"
#include "Bezier.h"

class GV00001 : public GVGeometry
{
public:
	GV00001();
	~GV00001();

	int getControlPointCount();
	void getControlPoints(std::vector<GVCoord>& vtxBuffer);
	GVCoord getControlPoint(int idx);
	void setControlPointsEx(std::vector<GVCoord>& vtxBuffer);
	void setControlPoints(std::vector<GVCoord>& vtxBuffer);
	void setControlPoint(int idx, const GVCoord& vtx);
	bool toVertice(std::vector<GVCoord>& vtxBuffer, std::vector<int>* vtxBuffer2);

private:
	std::vector<GVCoord> _controlPoints;
};

#endif //__GV00001_H__