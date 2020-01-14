package cn.smartcity.relationtran.oldModel.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性集合
 *
 * @author zffp
 */
public class Attributes {

    private Long id;

    private List<Attribute> attributeList;

    public Attributes() {
        attributeList = new ArrayList<>();
    }

    public Attributes(Long id, List<Attribute> attributes) {
        this.id = id;
        this.attributeList = attributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }


}
