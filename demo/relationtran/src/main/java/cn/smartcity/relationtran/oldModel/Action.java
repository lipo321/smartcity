package cn.smartcity.relationtran.oldModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 操作动作
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public class Action {

    /**
     * 操作方式
     */
	public static final int ADDING = 1;
	public static final int DELETE = 2;
	public static final int MODIFY = 4;
	public static final int COPY = 8;

    /**
     * 对象属性的编辑标记
     */
	public static final int BASE = 32;
	public static final int ATTRIBUTE = 64;
	public static final int FORM = 128;
	public static final int RELEATION = 256;
	public static final int COMPOSE = 512;
	public static final int MODEL = 1024;
    public static final int POSITION = 2048;

	/**
	 * 对象操作的唯一标识
	 */
    private Long id;

	/**
	 * 操作，为操作类型与操作方面做或运算后得到的值，例如修改属性字段为：68=4|64。
	 * 注意：原则上操作类型和操作方面各只能一种
	 */
	private Integer operation;

    /**
     * 导致该操作的事件（状态事件模型）
     * 注意：可能多个事件对应一个操作
     */
    private ActionEvent ae;
    
    /**
     * 操作的实例对象
     * 注意：在查询某个版本所对应Action时，该对象有值，否则都为空
     */
    private OBase obj;
    
    /**
     * 该操作对应的版本
     * 注意：在查询对象所包含Action时，该对象有值，否则都为空
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Version version;
    
    /**
     * 操作时间片段【起始时间】
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sTime = new Date();
    
    /**
     * 操作时间片段【终止时间】
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eTime = new Date();
    
    // 操作的对象的八个方面的id，细粒度
    private Long mId;

    public Action() {

    }
    
    public Action(Long aid) {
    	this.id = aid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public ActionEvent getAe() {
        return ae;
    }

    public void setAe(ActionEvent ae) {
        this.ae = ae;
    }

    public OBase getObj() {
        return obj;
    }

    public void setObj(OBase obj) {
        this.obj = obj;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

	public Date getsTime() {
		return sTime;
	}

	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }
}
