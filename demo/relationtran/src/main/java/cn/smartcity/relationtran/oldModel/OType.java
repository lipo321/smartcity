package cn.smartcity.relationtran.oldModel;

import cn.smartcity.relationtran.oldModel.attribute.Field;
import cn.smartcity.relationtran.oldModel.attribute.Fields;
import cn.smartcity.relationtran.oldModel.form.FormStyle;
import cn.smartcity.relationtran.oldModel.form.FormStyles;
import cn.smartcity.relationtran.oldModel.reference.SpatialReferenceSystem;
import cn.smartcity.relationtran.oldModel.reference.TimeReferenceSystem;

import java.util.List;

/**
 * 对象类模板
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 * 2、xhlijava@qq.com于2017年12月28日新增Mtime字段
 */

public class OType extends AObject {

    /**
     * 标签
     */
    private String tags;

    /**
     * 描述
     */
    private String des;

    /**
     * 图标
     */
    private String icon;

    /**
     * 编码
     */
    private String code;

    /**
     * 空间参照
     */
    private SpatialReferenceSystem srs;

    /**
     * 时间参照
     */
    private TimeReferenceSystem trs;

    /**
     * 字段集合
     */
    private Fields     fields;
    /**
     * 形态样式
     */
    private FormStyles formStyles;
    /**
     * 连接关系
     */
    private Connectors connectors;
    /**
     * 行为列表
     */
    //private Models     models;

    /**
     * 位置
     */
    private Integer placedes;

    //private EditTypeEnum editType = EditTypeEnum.base;

    private User user;

    private Long mtime;

    public OType() {
        formStyles = new FormStyles();
        connectors = new Connectors();
        //   models = new Models();
        fields = new Fields();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public SpatialReferenceSystem getSrs() {
        return srs;
    }

    public void setSrs(SpatialReferenceSystem srs) {
        this.srs = srs;
    }

    public TimeReferenceSystem getTrs() {
        return trs;
    }

    public void setTrs(TimeReferenceSystem trs) {
        this.trs = trs;
    }

    public Connectors getConnectors() {
        return connectors;
    }

    public void setConnectors(Connectors connectors) {
        this.connectors = connectors;
    }

    public FormStyles getFormStyles() {
        return formStyles;
    }

    public void setFormStyles(FormStyles formStyles) {
        this.formStyles = formStyles;
    }

    public Integer getPlacedes() {
        return placedes;
    }

    public void setPlacedes(Integer placedes) {
        this.placedes = placedes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 添加字段集合
     */
    public void addFields(List<Field> fields) {
        if (this.fields != null && this.fields.getFields() != null) {
            this.fields.addFields(fields);
        }
    }


    /**
     * 添加形态样式集合
     */
    public void addFormStyles(List<FormStyle> formStyles) {
        if (this.formStyles != null && this.formStyles.getStyles() != null) {
            this.formStyles.addStyles(formStyles);
        }
    }

    /**
     * 添加样式
     *
     * @param formStyle
     */
    public void addStyle(FormStyle formStyle) {
        this.formStyles.addStyle(formStyle);
    }

    /**
     * 添加连接关系集合
     */
    public void addConnectors(List<Connector> connectors) {
        if (this.connectors != null && this.connectors.getConnectors() != null) {
            this.connectors.addConnectors(connectors);
        }
    }
}
