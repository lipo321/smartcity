package cn.smartcity.relationtran.oldModel.attribute;

import cn.smartcity.relationtran.oldModel.AObject;
import cn.smartcity.relationtran.oldModel.User;
import cn.smartcity.relationtran.oldModel.attribute.dict.DTypeEnum;
import cn.smartcity.relationtran.oldModel.attribute.dict.FieldInputEnum;
import cn.smartcity.relationtran.oldModel.attribute.dict.UnitDict;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 字段
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 * 2、xhlijava@qq.com于2017年12月27日增加mtime
 */
public class Field extends AObject {

    /**
     * 标题
     */
    private String         caption;
    /**
     * 描述
     */
    private String         des;
    /**
     * 图标
     */
    private String         icon;
    /**
     * 字段类型
     */
    private DTypeEnum      type   = DTypeEnum.TEXT;
    /**
     * 字段输入UI类型
     */
    private FieldInputEnum uitype = FieldInputEnum.Text;
    /**
     * 所属用户
     */
    private User           user;
    /**
     * 值域，字段取值的限制描述，格式为:limitname:[],例如:
     * Range=["高速公路", "省道", "县道"],描述一个范围取值;
     * Domain=[21, 34],描述一个值域,[0]-最小值;[1]-最大值.若多个值域,则依次成对描述:[2]-最小值;[3]-最大值，数组长度必须为偶数;
     * Email=[]
     * PhoneNumber=[]
     * Length=[255]
     */
    private String         domain;

    /**
     * 默认值（缺省值）
     */
    private Object defaultValue;

    /**
     * 单位
     */
    private UnitDict unit;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long mtime;

    /**
     * @roseuid 59EF033603B5
     */
    public Field() {

    }

    public Field(Long id) {
        this.setId(id);
    }

    public DTypeEnum getType() {
        return type;
    }

    public void setType(DTypeEnum type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public UnitDict getUnit() {
        return unit;
    }

    public void setUnit(UnitDict unit) {
        this.unit = unit;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public FieldInputEnum getUitype() {
        return uitype;
    }

    public void setUitype(FieldInputEnum uitype) {
        this.uitype = uitype;
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

    @Override
    public String toString() {
        return getId().toString();
    }

}
