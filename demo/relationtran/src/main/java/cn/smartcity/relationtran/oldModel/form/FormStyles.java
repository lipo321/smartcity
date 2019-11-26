package cn.smartcity.relationtran.oldModel.form;

import cn.smartcity.relationtran.oldModel.form.dict.FormEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 形态样式集合
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史： 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public class FormStyles {

    private List<FormStyle> styles;

    public FormStyles() {
        styles = new ArrayList<>();
    }

    public FormStyles(List<Long> formStyleIds) {
        List<FormStyle> result = new ArrayList<FormStyle>();
        for (long id : formStyleIds) {
            FormStyle formStyle = new FormStyle(id);
            result.add(formStyle);
        }
        this.styles = result;
    }

    public List<FormStyle> getStyles() {
        return styles;
    }

    /**
     * 通过形态类型，获得形态样式对象
     * @param type FormEnum 形态类型
     * @return FormStyle
     */
    public FormStyle getFormStyleByType(int type) {

        if (styles != null && styles.size()>0) {
            for (FormStyle formStyle : styles) {
                FormEnum formEnum = formStyle.getType();
                if (formEnum == null) {
                    return null;
                }
                if (type == formEnum.getValue()) {
                    return formStyle;
                }
            }
        }
        return null;
    }

    public void setStyles(List<FormStyle> styles) {
        this.styles = styles;
    }

    public void addStyles(List<FormStyle> styles){
        for (FormStyle style : styles) {
            boolean exit = false;
            for (FormStyle formStyle : this.styles) {
                if (formStyle.getType().getName().equals(style.getType().getName())){
                    exit = true;
                }
            }
            if (!exit){
                this.styles.add(style);
            }
        }
    }

    public void addStyle(FormStyle formStyle){
        if(formStyle!=null && formStyle.getType()!=null && formStyle.getType().getValue() > 0){
            this.styles.add(formStyle);
        }
    }
    /**
     * 將集合转变成ID列表
     * @return List<Long>
     */
    public List<Long> toList() {
        return this.styles.stream().map(f -> f.getId()).collect(Collectors.toList());
    }

    /**
     * 將List集合转变成字符串
     * @return  String
     */
    @Override
    public String toString() {
        String result = "[]";
        if (styles != null) {
            List<Long> formStyleIdList = this.styles.stream().map(f -> f.getId()).collect(Collectors.toList());
            result = formStyleIdList.toString();
        }
        return result;
    }
}
