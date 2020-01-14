package cn.smartcity.relationtran.oldModel;


import java.util.List;

/**
 * 版本
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */

public class Version {
    /**
     * 版本ID
     */
    private Long         vid;
    /**
     * 版本描述信息
     */
    private String       msg;
    /**
     * 操作类型
     * 说明：该操作是所有相关Action中operation的或
     * 例如：值为ADDING|MODIFY
     */
    private Integer      operate;
    /**
     * 创建时间
     */
    private Long         vtime;
    /**
     * 所基于的版本号
     */
    private Long         rvid;
    /**
     * 操作动作集合
     * 注意：只有在查询某个版本所包含的Action时才有值，否则都为空
     */
    private List<Action> oactions;
    /**
     * 所属用户
     */
    private User         user;

    /**
     * @roseuid 59EEF58902D8
     */
    public Version() {

    }

    public Long getVid() {
        return vid;
    }

    public void setVid(Long vid) {
        this.vid = vid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public Long getVtime() {
        return vtime;
    }

    public void setVtime(Long vtime) {
        this.vtime = vtime;
    }

    public Long getRvid() {
        return rvid;
    }

    public void setRvid(Long rvid) {
        this.rvid = rvid;
    }

    public List<Action> getOactions() {
        return oactions;
    }

    public void setOactions(List<Action> oactions) {
        this.oactions = oactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
