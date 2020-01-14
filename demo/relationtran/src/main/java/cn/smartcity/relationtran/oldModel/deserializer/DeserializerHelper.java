package cn.smartcity.relationtran.oldModel.deserializer;

import cn.smartcity.relationtran.oldModel.deserializer.dict.ConstantDict;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.*;


/**
 * 反序列化辅助类
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class DeserializerHelper {

    public static List<Map<String, Object>> deserializer(JsonParser parser) throws IOException {

        List<Map<String, Object>> resultList = new ArrayList<>();
        JsonNode jsonNode = parser.getCodec().readTree(parser);

        if (jsonNode.isArray()) {
            for (Iterator<JsonNode> iter = jsonNode.iterator(); iter.hasNext(); ) {
                JsonNode jNode = iter.next();
                Map<String, Object> map = new HashMap<>(16);
                buildMap(jNode, map);
                resultList.add(map);
            }
        }

        return resultList;

    }

    /**
     * 递归重组JSON对象
     *
     * @param jsonNode
     * @param map
     */
    public static void buildMap(JsonNode jsonNode, Map<String, Object> map) {

        for (Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields(); iter.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = iter.next();
            JsonNode valueNode = entry.getValue();
            if (valueNode.isTextual()) {
                map.put(entry.getKey(), valueNode.asText());
            } else if (valueNode.isFloat() || valueNode.isDouble()) {
                map.put(entry.getKey(), valueNode.asDouble());
            } else if (valueNode.isInt()) {
                map.put(entry.getKey(), valueNode.asInt());
            } else if (valueNode.isLong()) {
                map.put(entry.getKey(), valueNode.asLong());
            } else if (valueNode.isBoolean()) {
                map.put(entry.getKey(), valueNode.asBoolean());
            } else if (valueNode.isObject()) {
                if (ConstantDict.GEOMETRY.equals(entry.getKey()) || ConstantDict.GEOM.equals(entry.getKey())) {
                    map.put(entry.getKey(), valueNode);
                } else {
                    buildMap(valueNode, map);
                }

            }
        }
    }

}
