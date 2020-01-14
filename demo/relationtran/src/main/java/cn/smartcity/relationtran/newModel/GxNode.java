package cn.smartcity.relationtran.newModel;

import java.util.List;
import java.util.Objects;

/**
 * <p>GxNode</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 16:13 2019/10/28
 */
public class GxNode {

    /**
     * 对象id
     */
    private Long   oid;
    /**
     * 工程id
     */
    private Long   prjId;
    /**
     * 对象类id
     */
    private Long   clsId;
    /**
     * 对象类名称
     */
    private String clsName;

    /**
     * 对象属性
     */
    private List<GxAttr> attrs;

    public GxNode() {
    }

    public Long getPrjId() {
        return prjId;
    }

    public void setPrjId(Long prjId) {
        this.prjId = prjId;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getClsId() {
        return clsId;
    }

    public void setClsId(Long clsId) {
        this.clsId = clsId;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public List<GxAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<GxAttr> attrs) {
        this.attrs = attrs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GxNode gxNode = (GxNode) o;
        return oid.equals(gxNode.oid) &&
                clsId.equals(gxNode.clsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid, clsId);
    }
}
