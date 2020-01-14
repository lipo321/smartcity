//Source file: E:\\projects\\OneGIS\\trunk\\src\\Server\\DataStore\\onegis.core\\src\\main\\java\\onegis\\psde\\relation\\RObject.java

package cn.smartcity.relationtran.oldModel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * 关系对象
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class RObject {
    private long                id;
    private Long                relatedObjectId;
    private String              label;
    private Map<String, Object> properties;


    /**
     * @roseuid 5A0B8B140102
     * @since 1.0.0
     */
    public RObject() {
        properties = new HashMap<>();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setRelatedObjectId(Long relatedObjectId) {
        this.relatedObjectId = relatedObjectId;
    }

    public Long getRelatedObjectId() {
        return relatedObjectId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @JsonAnySetter
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Object getProperty(String key) {
        return properties.get(key);
    }

    public void removeProperty(String key) {
        properties.remove(key);
    }


    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

}
