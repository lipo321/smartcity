package com.example.demo.tools;

import com.example.demo.entity.OsmObject;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 将内存OSM对象转换成JSON，使用了Jackson插件
 *
 * @author lipo@126.com
 * @version 2019年7月4日
 */
public class JsonUtils {

    public static void ObjectsToJson(List<OsmObject> objects, File outFile)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonGenerator generator =
                mapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);

        generator.writeObject(objects);

        //将Json输出到指定文件中
        mapper.writeValue(outFile, objects);
    }
}
