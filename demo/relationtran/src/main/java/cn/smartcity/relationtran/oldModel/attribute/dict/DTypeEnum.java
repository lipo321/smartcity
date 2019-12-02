package cn.smartcity.relationtran.oldModel.attribute.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据类型枚举
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、xtpgkxk@163.com 于2017年10月25日设计并构建初始版本v1.0.0
 */
public enum DTypeEnum {

	/**
	 * SHORT
	 */
    SHORT("short", 1),
	/**
	 *LONG
	 */
    LONG("long", 2),
	/**
	 *FLOAT
	 */
    FLOAT("float", 3),
	/**
	 *DOUBLE
	 */
    DOUBLE("double", 4),
	/**
	 *TEXT
	 */
    TEXT("text", 5),
	/**
	 *DATE
	 */
    DATE("date", 6),

    /**
     * int
     */
    INTEGER("int", 7),

    /**
     * BOOLEAN
     */
    BOOLEAN("boolean", 8);
	
	private static String[] OBJTYPES = new String[]{
			Short.class.getName(), 
			Long.class.getName(),
			Float.class.getName(),
			Double.class.getName(),
			String.class.getName(),
			Date.class.getName()};
    private final String name;

    private int value;

    DTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    public static Object getObjType(int value) {
    	String clazz = OBJTYPES[value-1];
    	try {
			return Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public static List<Map<String, Object>> enumList() {
    	List<Map<String, Object>> list = new ArrayList<>();
    	for(DTypeEnum dtypeEnum : DTypeEnum.values()){
    		Map<String, Object> map = new HashMap<>();
    		map.put("name", dtypeEnum.getName());
    		map.put("value", dtypeEnum.getValue());
    		list.add(map);
    	}
    	return list;
    }
    
    /**
     * 根据value获取枚举对象
     * @param value
     * @return
     */
    @JsonCreator
    public static DTypeEnum getEnum(int value){
    	for(DTypeEnum dtypeEnum : DTypeEnum.values()){
    		if(dtypeEnum.getValue() == value){
    			return dtypeEnum;
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

