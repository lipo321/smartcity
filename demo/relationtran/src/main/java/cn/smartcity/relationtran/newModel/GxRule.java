package cn.smartcity.relationtran.newModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>GxRule</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 15:55 2019/10/30
 */
public class GxRule {
    /** 图数据库主键 */
    @JsonIgnore
    private Long rid;

    /** relation的hash主键 */
    @JsonIgnore
    private Long hashId;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 规则名称
     */
    private String name;

    public GxRule() {
    }

    public GxRule(Long rid) {
        this.rid = rid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getHashId() {
        return hashId;
    }

    public void setHashId(Long hashId) {
        this.hashId = hashId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
}
