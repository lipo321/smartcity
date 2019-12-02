package cn.smartcity.relationtran.oldModel.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关系类型
 * @author  zffp
 */
public enum RelationEnum {
	/**
	 * 一对一
	 */
    ONETOONE("一对一", 1),
	/**
	 * 一对多
	 */
    ONETOMANY("一对多", 2),
	/**
	 * 多对一
	 */
    MANYTOONE("多对一", 3),
	/**
	 * 多对多
	 */
    MANYTOMANY("多对多", 4);

	private final String name;
	
	private final int value;

    RelationEnum(String name, int value) {
        this.value = value;
        this.name = name;
    }
    
    public static List<Map<String, Object>> enumList() {
    	List<Map<String, Object>> list = new ArrayList<>();
    	for(RelationEnum relationEnum : RelationEnum.values()){
    		Map<String, Object> map = new HashMap<>();
    		map.put("name", relationEnum.getName());
    		map.put("value", relationEnum.getValue());
    		list.add(map);
    	}
    	return list;
    }
    
    @JsonCreator
    public static RelationEnum getEnum(int value){
    	for(RelationEnum relationEnum : RelationEnum.values()){
    		if(relationEnum.getValue() == value){
    			return relationEnum;
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
