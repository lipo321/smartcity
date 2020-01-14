package com.example.demo.shapeEntity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
@Getter
@Setter
public class ShapeFieldInfo implements Serializable {

    private static final long serialVersionUID = 1401662430170057723L;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段类型
     */
    private Class<?> fieldType = null;


    @Override
    public String toString() {
        return "fieldName:" + fieldName + "|fieldType:" + fieldType.getTypeName();
    }

}
