package cn.smartcity.relationtran.newModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>GxAttr</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 15:55 2019/10/30
 */
public class GxAttr {
    @JsonIgnore
    private Long aid;

    @JsonIgnore
    private Long oid;

    @JsonIgnore
    private Long hashId;

    /**
     * 字段id
     */
    private Long   fid;
    /**
     * 属性名称
     */
    private String name;
    /**
     * 属性值
     */
    private Object value;

    public GxAttr() {
    }

    public GxAttr(Long aid) {
        this.aid = aid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getHashId() {
        return hashId;
    }

    public void setHashId(Long hashId) {
        this.hashId = hashId;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
