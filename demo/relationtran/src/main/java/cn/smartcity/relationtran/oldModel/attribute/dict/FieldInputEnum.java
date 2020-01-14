package cn.smartcity.relationtran.oldModel.attribute.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字段输入UI枚举
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、xtpgkxk@163.com 于2017年10月25日设计并构建初始版本v1.0.0
 */
public enum FieldInputEnum {
    /**
     * 文本
     */
    Text("文本", "Text"),

    /**
     * 数字
     */
    Number("数字", "Number"),

    /**
     * 日期
     */
    Date("日期", "Date"),

    UploadImg("图片上传", "UploadImg"),
    UploadFile("文件上传", "UploadFile"),

    /**
     * 下拉选择
     */
    Select("下拉选择", "Select"),

    /**
     * 单选框
     */
    Radio("单选框", "Radio"),

    /**
     * 多选框
     */
    Checkbox("多选框", "Checkbox"),

    /**
     * 滑块
     */
    Slider("滑块", "Slider"),

    /**
     * 多行文本
     */
    Textarea("多行文本", "Textarea");

    private final String name;
    private final String value;

    FieldInputEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static List<Map<String, Object>> enumList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (FieldInputEnum fieldInputEnum : FieldInputEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", fieldInputEnum.getName());
            map.put("value", fieldInputEnum.getValue());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据value获取枚举对象
     *
     * @param value
     * @return
     */
    @JsonCreator
    public static FieldInputEnum getEnum(String value) {
        for (FieldInputEnum fieldInputEnum : FieldInputEnum.values()) {
            if (value.equals(fieldInputEnum.getValue())) {
                return fieldInputEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
