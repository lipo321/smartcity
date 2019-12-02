package cn.smartcity.relationtran.newModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lipoGiser
 * @date 2019/11/26 14:18
 * @Version 0.1
 */
public enum EGxRelationState {
    KEEP(0,"关系开始"),
    BREAK(1,"关系结束");

    private final Integer code;
    private final String content;

    EGxRelationState(Integer code,String content){
        this.code = code;
        this.content = content;
    }


    public Integer getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    /**
     * 解析code为EGxRelationState
     * @param code code值
     * @return EGxRelationState 对应枚举
     */
    public static EGxRelationState analysisCode(Integer code) {
        List<EGxRelationState> enumList = Arrays.asList(EGxRelationState.values());
        Map<Integer, EGxRelationState> codeMap = enumList.stream().collect(Collectors.toMap(EGxRelationState::getCode, type -> type));
        return codeMap.get(code);
    }
}
