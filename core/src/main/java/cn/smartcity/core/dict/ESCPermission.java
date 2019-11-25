package cn.smartcity.core.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lipoGiser
 * @date 2019/11/22 16:33
 * @Version 0.1
 */
public enum ESCPermission {
    PRIVATE(0,"只有本人可以编辑和查看"),
    PROTECTED(1,"所有人都可以查看，只有本人可标记"),
    PUBLIC(2,"所有人都可以编辑");

    private final Integer code;
    private final String  name;

    ESCPermission(Integer code,String name){
        this.name = name;
        this.code = code;
    }

    /**
     * 根据code获取枚举对象
     * @param code 码值
     * @return GxDimensionEnum
     */
    @JsonCreator
    public static ESCPermission getEnum(int code){
        for(ESCPermission dTypeEnum : ESCPermission.values()){
            if(dTypeEnum.getCode() == code){
                return dTypeEnum;
            }
        }
        return null;
    }

    @JsonValue
    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
