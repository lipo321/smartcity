package cn.smartcity.core.model;

import cn.smartcity.core.dict.ESCPermission;

import java.util.Date;

/**
 * @author lipoGiser
 * @date 2019/11/22 16:30
 * @Version 0.1
 */
public class SCVersion {
    private String name;
    private Long   owner;
    private String des;
    private Date   cTime;
    private Date   mTime;
    private Long   rvid;
    private ESCPermission  escPermission = ESCPermission.PRIVATE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public Long getRvid() {
        return rvid;
    }

    public void setRvid(Long rvid) {
        this.rvid = rvid;
    }
}
