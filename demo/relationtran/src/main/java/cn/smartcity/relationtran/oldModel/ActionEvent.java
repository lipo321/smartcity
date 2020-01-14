package cn.smartcity.relationtran.oldModel;

/**
 * 操作事件
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public class ActionEvent {
    /**
     * 事件标记
     */
    private Integer id;

    /**
     * 事件类型
     * 目的：区分事件
     */
    private String type;

    /**
     * 事件名称
     */
    private String name;

    /**
     * 产生该事件原因
     */
    private String reason;

    /**
     * 操作执行的事件对象模型
     */
    private Integer eventModel;

    /**
     * 模型初始化数据
     */
    private String mInitData;

    /**
     * @roseuid 59EEF58901D6
     */
    public ActionEvent() {

    }

    public ActionEvent(Integer op) {
        //this.operation = op;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getEventModel() {
        return eventModel;
    }

    public void setEventModel(Integer eventModel) {
        this.eventModel = eventModel;
    }

    public String getmInitData() {
        return mInitData;
    }

    public void setmInitData(String mInitData) {
        this.mInitData = mInitData;
    }
}
