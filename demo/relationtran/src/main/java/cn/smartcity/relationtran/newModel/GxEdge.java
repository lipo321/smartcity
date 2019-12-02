package cn.smartcity.relationtran.newModel;


import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>GxRelationEdge</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 16:00 2019/10/28
 */
public class GxEdge {

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
    private Integer state = EGxRelationState.KEEP.getCode();

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

    public GxEdge() {
    }

    public GxRelation build() {
        GxRelation gxRelation = new GxRelation();
        BeanUtils.copyProperties(this,gxRelation);
        return gxRelation;

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
