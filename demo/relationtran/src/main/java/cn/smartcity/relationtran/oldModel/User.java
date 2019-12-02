package cn.smartcity.relationtran.oldModel;

/**
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库，用户及权限
 * @note 修订历史： 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 * 2、xhlijava@qq.com于2017年12月27日增加userNickName、userAvatar
 */

public class User {
    /**
     * 用户没有读写权限
     */
    public final static int NULL = 0;

    /**
     * 用户具有读权限
     */
    public final static int READ = 1;

    /**
     * 用户具有写权限
     */
    public final static int WRITE = 2;

    /**
     * 普通用户角色
     */
    public static final int USER = 4;

    /**
     * 管理员角色，可以管理普通用户
     */
    public static final int MANAGER = 16;

    /**
     * 超户角色，可以管理任何用户
     */
    public static final int SUPER = 64;

    /**
     * 用户唯一标识
     */
    private String uid;
    
    /**
     * 用户昵称
     */
    private String userNickName;
    
    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户角色，其值为USER|MANAGER|SUPER任意组合
     */
    private Integer role;

    /**
     * 用户读写权限，其值为NULL|READ|WRITE任意组合
     */
    private Integer rw;

    /**
     * 用户数据读取权限级别
     */
    private Integer level;

    /**
     * @roseuid 59EEF5890197
     */
    public User() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getRw() {
        return rw;
    }

    public void setRw(Integer rw) {
        this.rw = rw;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
}
