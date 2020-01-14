package cn.smartcity.relationtran.oldModel.deserializer;

import cn.smartcity.relationtran.oldModel.AObject;
import cn.smartcity.relationtran.oldModel.Action;
import cn.smartcity.relationtran.oldModel.OBase;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class ChildrenDeserializer extends JsonDeserializer<List<AObject>> {

    @Override
    public List<AObject> deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        List<AObject> result = new ArrayList<>();
        List<Map<String, Object>> dataList = DeserializerHelper.deserializer(parser);
        for (Map<String, Object> map : dataList) {
            OBase obase = new OBase();
            ArrayList<Action> actions = new ArrayList<>();
            Action action = new Action();
            action.setOperation(Integer.valueOf(Action.ADDING));
            actions.add(action);
            obase.setActions(actions);
            obase.setId(Long.parseLong(map.get("id").toString()));
            result.add(obase);
        }
        return result;
    }

}
