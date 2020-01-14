//Source file: E:\\projects\\OneGIS\\trunk\\src\\Server\\DataStore\\onegis.core\\src\\main\\java\\onegis\\psde\\relation\\RNode.java

package cn.smartcity.relationtran.oldModel;

import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * 节点
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class RNode extends RObject {

    private REdge edge = null;
    /**
     * 对象的otype，
     */
    private OType oType;

    /**
     * 加个位置
     */

    private RPoint point;


    public RNode() {

    }

    public RNode(REdge edge) {
        setEdge(edge);
    }

    public void setEdge(REdge edge) {
        this.edge = edge;
    }

    public REdge getEdge() {
        return edge;
    }


    public OType getoType() {
        return oType;
    }

    public void setoType(OType oType) {
        this.oType = oType;
    }

    public RPoint getPoint() {
        return point;
    }

    public void setPoint(RPoint point) {
        this.point = point;
    }

    @JsonAnySetter
    public String getOTypeName() {
        boolean nameNoValid = this.oType == null || this.oType.getName() == null || "".equals(this.oType.getName());

        if (nameNoValid) {
            return "Object";
        }
        return this.oType.getName();
    }


}
