package cn.smartcity.relationtran.oldModel.reference;

import java.io.Serializable;
import java.util.Date;

/**
 * @author flw
 * @description 用户空间参考
 * @company 苏州中科蓝迪
 * @date 2019/7/9 9:12
 */

public class SpatialRefSysCustom implements Serializable {

    private String id;

    private String srId;

    private String authName;

    private String srText;

    private String name;

    private Long uid;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrId() {
        return srId;
    }

    public void setSrId(String srId) {
        this.srId = srId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getSrText() {
        return srText;
    }

    public void setSrText(String srText) {
        this.srText = srText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
