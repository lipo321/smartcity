package cn.smartcity.relationtran.oldModel;

import cn.smartcity.relationtran.oldModel.attribute.Fields;
import cn.smartcity.relationtran.oldModel.dict.RelationEnum;

/**
 * 连接关系
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史： 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 * 2、xhlijava@qq.com于2017年12月28日新增Mtime字段
 * 3、xhlijava@qq.com于2019年7月1日新增code字段（关系类别码）
 */
public class Relation extends AObject {

    /**
     * 关系映射类型，分为一对一(1)、一对多(2)、多对一(3)、多对多(4)
     */
	private RelationEnum mappingType = RelationEnum.ONETOONE;

    /**
     * 描述关系的字段
     */
	private Fields fields;

    /**
     * 关联行为模型
     */
	//public Model model;

    /**
     * 关系创建者
     */
	private User user;

    /**
     * 修改时间
     */
	private Long mtime;
	/**
	 * 类别码
	 */
	private String code;

	public Relation() {
		fields = new Fields();
	}

	public RelationEnum getMappingType() {
		return mappingType;
	}

	public void setOor(RelationEnum oor) {
		this.mappingType = oor;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getMtime() {
		return mtime;
	}

	public void setMtime(Long mtime) {
		this.mtime = mtime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
