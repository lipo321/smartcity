package cn.smartcity.relationtran.newModel;

import cn.smartcity.relationtran.newModel.bean.GxRelationBean;

import java.util.List;

/**
 * <p>GxRelation</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 11:42 2019/10/29
 */
public class GxRelation {

    private Long rid;

    private GxNode from;

    private GxNode to;
    /**
     * 关系类id
     */
    private Long   typeId;
    /**
     * 关系类名称
     */
    private String typeName;

    /**
     * update time 关系更新时间
     */

    private Long uTime;

    /**
     * 更新时间的时间参考，例如：UTC
     */
    private String trs;

    /**
     * 关系状态
     * 0 - 连接，1 - 断开
     */
    private Integer state;

    /**
     * 工程id
     */
    private Long prjId;

    /**
     * 关系属性
     */
    private List<GxAttr> attrs;

    /**
     * 关系规则
     */
    private List<GxRule> rules;

    /**
     * 关系强度
     */
    private Double intensity;


    public GxRelation() {
    }

    public GxRelationBean build() {
        return new GxRelationBean(this.rid, this.uTime);
    }


    public Long getPrjId() {
        return prjId;
    }

    public void setPrjId(Long prjId) {
        this.prjId = prjId;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public GxNode getFrom() {
        return from;
    }

    public void setFrom(GxNode from) {
        this.from = from;
    }

    public GxNode getTo() {
        return to;
    }

    public void setTo(GxNode to) {
        this.to = to;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getuTime() {
        return uTime;
    }

    public void setuTime(Long uTime) {
        this.uTime = uTime;
    }

    public String getTrs() {
        return trs;
    }

    public void setTrs(String trs) {
        this.trs = trs;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<GxAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<GxAttr> attrs) {
        this.attrs = attrs;
    }

    public List<GxRule> getRules() {
        return rules;
    }

    public void setRules(List<GxRule> rules) {
        this.rules = rules;
    }

    public Double getIntensity() {
        return intensity;
    }

    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }
}
