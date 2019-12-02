package cn.smartcity.relationtran.oldModel;

import cn.smartcity.relationtran.oldModel.deserializer.ChildrenDeserializer;
import cn.smartcity.relationtran.oldModel.deserializer.ParentsDeserializer;
import cn.smartcity.relationtran.oldModel.deserializer.ParentsSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础对象
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史： 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 *       2、xhli@qq.com于2018年5月10日将children类型改为List
 *       <Long>(children在返回时需要按照指定顺序，hashTable无法维持原有顺序)
 *
 */
public class OBase extends AObject {

	/**
	 * 信息唯一标识
	 */
	private Long uuid;

	/**
	 * 数据所来源对象标识
	 */
	private Long from;


    /**
     * 对象属于哪一个时空域
     */
	private Long sdomain;

	/**
	 * 子对象
	 */
	@JsonDeserialize(using = ChildrenDeserializer.class)
	private List<OBase> children = new ArrayList<>();

	/**
	 * 对象的父对象
	 */
	@JsonDeserialize(using = ParentsDeserializer.class)
	@JsonSerialize(using = ParentsSerialize.class)
	private List<OBase> parents = new ArrayList<>();

	/**
	 * 创建版本
	 */
	private Version cversion;
	/**
	 * 消亡版本
	 */
	private Version dversion;

	/**
	 * 当前版本
	 */
	private Version version;

	/**
	 * @roseuid 59EF00FC0331
	 */

	public OBase(Long uuid) {
		this.uuid = uuid;
	}

	public OBase() {

	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public List<OBase> getChildren() {
		return children;
	}

	public void setChildren(List<OBase> children) {
		this.children = children;
	}

	public List<OBase> getParents() {
		return parents;
	}

	public void setParents(List<OBase> parents) {
		this.parents = parents;
	}

	public Version getCversion() {
		return cversion;
	}

	public void setCversion(Version cversion) {
		this.cversion = cversion;
	}

	public Version getDversion() {
		return dversion;
	}

	public void setDversion(Version dversion) {
		this.dversion = dversion;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

    public Long getSdomain() {
        return sdomain;
    }

    public void setSdomain(Long sdomain) {
        this.sdomain = sdomain;
    }

    public void setParentsByIDs(List<Long> parentsIds) {
		List<OBase> parents = new ArrayList<>();
		for (Long parentId : parentsIds) {
			OBase obase = new OBase();
			obase.setId(parentId);
			parents.add(obase);
		}
		this.parents = parents;
	}
}
