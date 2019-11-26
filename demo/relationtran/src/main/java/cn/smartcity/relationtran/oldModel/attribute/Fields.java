package cn.smartcity.relationtran.oldModel.attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字段集合
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史： 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public class Fields {

	private List<Field> fields;

	public Fields() {
		fields = new ArrayList<>();
	}

	public Fields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * 將字段集合转变成字段ID列表
	 * @return 返回字段集合
	 */
	public List<Long> toList() {
		return this.fields.stream().map(f -> f.getId()).collect(Collectors.toList());
	}

	/**
	 * 添加字段
	 * @param field 字段
	 */
	public void addField(Field field) {
		this.fields.add(field);
	}

	public void addFields(List<Field> fields) {
		this.fields.addAll(fields);
	}

	/**
	 * 將字段集合转变成字段ID列表
	 */
	@Override
	public String toString() {
		String result = "[]";
		if (fields != null) {
			List<Long> fieldIdList = this.fields.stream().map(f -> f.getId()).collect(Collectors.toList());
			result = fieldIdList.toString();
		}
		return result;
	}

}
