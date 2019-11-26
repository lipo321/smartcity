package cn.smartcity.relationtran.oldModel.form.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 形态字典
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */

public enum FormEnum {
	/**
	 * 位置形态
	 */
	NULL("NULL",-1),
	/**
	 * 几何形态
	 */
    GEOMETRY("Geometry", 20),
	/**
	 * 点形态
	 */
    POINT("Point", 21),
	/**
	 * 线形态
	 */
    LINESTRING("Linestring", 22),
	/**
	 * 简单多边形
	 */
    POLYGON("Polygon", 23),

	/**
	 * 地形
	 */
    GEOGRAPHY("Geography", 30),
	/**
	 * 等高线
	 */
	ISOHYPSE("Isohypse", 31),
	/**
	 * dem
	 */
    DEM("Dem", 32),
	/**
	 * tin
	 */
	TIN("Tin", 33),
	/**
	 * bim
	 */
	BIM("Bim", 40),
	/**
	 * 模型
	 */
	MODEL("Model", 50),
	/**
	 * 球体
	 */
    SHAPEBLOCK("ShapeBlock",60),
	/**
	 * 椭球
	 */
	ELLIPSOID("Ellipsoid", 61),
	/**
	 * 三角面片
	 */
	TRIANGULARMESH("TriangularMesh", 62);
	
    private final int value;
    private final String name;

    FormEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    public static List<Map<String, Object>> enumList() {
    	List<Map<String, Object>> list = new ArrayList<>();
    	for(FormEnum formEnum : FormEnum.values()){
    		Map<String, Object> map = new HashMap<>();
    		map.put("name", formEnum.getName());
    		map.put("value", formEnum.getValue());
    		list.add(map);
    	}
    	return list;
    }
    
    public static List<Map<String, Object>> enumList(int startNum, int endNum){
    	List<Map<String, Object>> list = new ArrayList<>();
    	for(FormEnum formEnum : FormEnum.values()){
    		if(formEnum.getValue() >= startNum && formEnum.getValue() < endNum) {
    			Map<String, Object> map = new HashMap<>();
        		map.put("name", formEnum.getName());
        		map.put("value", formEnum.getValue());
        		list.add(map);
    		}
    	}
    	return list;
    }
    
    /**
     * 根据value获取枚举对象
     * @param value
     * @return
     */
    @JsonCreator
    public static FormEnum getEnum(int value){
    	for(FormEnum formEnum : FormEnum.values()){
    		if(formEnum.getValue() == value){
    			return formEnum;
    		}
    	}
    	return null;
    }
	/**
	 * 根据name获取枚举对象
	 * @param name
	 * @return
	 */
	public static FormEnum getEnum(String name){
		for(FormEnum formEnum : FormEnum.values()){
			if(formEnum.getName().equals(name) ){
				return formEnum;
			}
		}
		return null;
	}

    @JsonValue
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
