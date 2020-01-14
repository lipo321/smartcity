package cn.smartcity.relationtran.oldModel.form.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 形态样式类型
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 0.1.0
 * @brief 数据访问引擎业务管理
 * @note 修订历史：
 * 1、xhlijava@qq.com于2017年10月27日设计并构建初始版本v0.1.0
 */
public enum FormStyleTypeEnum {
    /**
     * sld样式
     */
    sld("sld", 1),
    /**
     * sld-css样式
     */
    SldCss("Sld-Css", 2),
    /**
     * mapbox-css样式
     */
    MapboxCss("Mapbox-Css", 3),
    /**
     * 模型
     */
    mesh("Mesh", 4),
    /**
     * 服务
     */
    server("server", 5);

    private final String name;

    private final int value;

    FormStyleTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;

    }

    public static List<Map<String, Object>> enumList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (FormStyleTypeEnum formStyleTypeEnum : FormStyleTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", formStyleTypeEnum.getName());
            map.put("value", formStyleTypeEnum.getValue());
            list.add(map);
        }
        return list;
    }

    @JsonCreator
    public static FormStyleTypeEnum getEnum(int value) {
        for (FormStyleTypeEnum formStyleTypeEnum : FormStyleTypeEnum.values()) {
            if (formStyleTypeEnum.getValue() == value) {
                return formStyleTypeEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
