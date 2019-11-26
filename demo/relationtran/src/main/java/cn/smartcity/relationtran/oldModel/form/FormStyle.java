package cn.smartcity.relationtran.oldModel.form;

import cn.smartcity.relationtran.oldModel.AObject;
import cn.smartcity.relationtran.oldModel.User;
import cn.smartcity.relationtran.oldModel.form.dict.FormEnum;
import cn.smartcity.relationtran.oldModel.form.dict.FormStyleTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * 形态样式
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史： 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public class FormStyle extends AObject {
	/**
	 * 形态描述
	 */
	private String            des;
	/**
	 * 形态样式类型 sld、svg、css、3ds、etc.
	 */
	private FormStyleTypeEnum style     = FormStyleTypeEnum.MapboxCss;
	/**
	 * 样式内容
	 */
	private String            data;
	/**
	 * 形态类型
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private FormEnum          type      = FormEnum.POINT;
	/**
	 * 位置
	 */
	private List<Integer>     positions = new ArrayList<>();
	/**
	 * 创建用户
	 */
	private User              user;

	/**
	 * 对象类形态样式额外信息 认知维度，认知最小尺度，认知最大尺度 注意：如果不在OType中存储，其值都为空
	 */
	private Integer dim;
	private Double minGrain;
	private Double maxGrain;
	
    /**
     * 操作时间
     */
    private Long mtime;

	public FormStyle() {

	}

	public FormStyle(long id) {
		this.setId(id);
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public FormStyleTypeEnum getStyle() {
		return style;
	}

	public void setStyle(FormStyleTypeEnum style) {
		this.style = style;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public FormEnum getType() {
		return type;
	}

	public void setType(FormEnum type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 对象类形态样式额外信息 认知维度，认知最小尺度，认知最大尺度
	 */
	public Integer getDim() {
		return dim;
	}

	public void setDim(Integer dim) {
		this.dim = dim;
	}

	public Double getMinGrain() {
		return minGrain;
	}

	public void setMinGrain(Double minGrain) {
		this.minGrain = minGrain;
	}

	public Double getMaxGrain() {
		return maxGrain;
	}

	public void setMaxGrain(Double maxGrain) {
		this.maxGrain = maxGrain;
	}

	public Long getMtime() {
		return mtime;
	}

	public void setMtime(Long mtime) {
		this.mtime = mtime;
	}

	public List<Integer> getPositions() {
		return positions;
	}

	public void setPositions(List<Integer> positions) {
		this.positions = positions;
	}

}
