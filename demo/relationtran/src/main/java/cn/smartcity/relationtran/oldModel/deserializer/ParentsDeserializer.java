package cn.smartcity.relationtran.oldModel.deserializer;

import cn.smartcity.relationtran.oldModel.OBase;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 父对象反序列化
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class ParentsDeserializer extends JsonDeserializer<List<OBase>> {

    @Override
    public List<OBase> deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        List<OBase> hashMap = new ArrayList<>();
        List<Map<String, Object>> dataList = DeserializerHelper.deserializer(parser);
        for (Map<String, Object> map : dataList) {
            Long oid = Long.parseLong(map.get("id").toString());
            OBase obase = new OBase();
            obase.setId(oid);
            hashMap.add(obase);
        }
        return hashMap;
    }

}
