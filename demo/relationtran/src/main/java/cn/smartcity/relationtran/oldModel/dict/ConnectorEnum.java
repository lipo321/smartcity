package cn.smartcity.relationtran.oldModel.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 连接关系枚举
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、xtpgkxk@163.com 于2017年10月25日设计并构建初始版本v1.0.0
 */

/**
 * 连接关系枚举
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 0.1.0
 * @brief 数据访问引擎业务管理
 * @note 修订历史：
 * 1、xhlijava@qq.com于2017年10月31日修改
 */
public enum ConnectorEnum {
    /**
     *
     */
    Relation("Relation", 0),
    /**
     * 实现
     */
    Realization("Realization", 2),
    /**
     * 聚合
     */
    Aggregation("Aggregation", 4),
    /**
     * 组合
     */
    Composition("Composition", 8),
    /**
     * 依赖
     */
    Dependency("Dependency", 16),
    /**
     * 关联
     */
    Association("Association", 32);

    private int    value;
    /**
     * 关系名称
     */
    private String name;


    ConnectorEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static List<Map<String, Object>> enumList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ConnectorEnum connectorEnum : ConnectorEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", connectorEnum.getName());
            map.put("value", connectorEnum.getValue());
            list.add(map);
        }
        return list;
    }

    @JsonCreator
    public static ConnectorEnum getEnum(int value) {
        for (ConnectorEnum connectorEnum : ConnectorEnum.values()) {
            if (connectorEnum.getValue() == value) {
                return connectorEnum;
            }
        }
        return null;
    }

    @JsonValue
    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }
}
