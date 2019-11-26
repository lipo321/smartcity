package cn.smartcity.relationtran.oldModel;

import cn.smartcity.relationtran.oldModel.dict.ConnectorEnum;

/**
 * 对象类连关系
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史： 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public class Connector extends AObject {

	/**
	 * 连接类型
	 */
	private ConnectorEnum type = ConnectorEnum.Relation;

	/**
	 * 关系
	 */
	private Relation relation;

	/**
	 * 用户
	 */
	private User user;

	/**
	 * 目标类模板
	 */
	private OType dType;
	
	/**
	 * 源类模板Id
	 * connector添加关系时 作为唯一标识使用
	 */
	private Long fId;
	
	/**
	 * @roseuid 59EF1E2C006B
	 */
	public Connector() {

	}

	public Connector(long id) {
		this.setId(id);
	}

	public ConnectorEnum getType() {
		return type;
	}

	public void setType(ConnectorEnum type) {
		this.type = type;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OType getdType() {
		return dType;
	}

	public void setdType(OType dType) {
		this.dType = dType;
	}

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

}
