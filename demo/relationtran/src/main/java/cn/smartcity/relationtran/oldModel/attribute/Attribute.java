package cn.smartcity.relationtran.oldModel.attribute;

/**
 * 对象属性描述
 * @author  zffp
 * @date
 */
public  class Attribute {
    private Long fid;
    private String name;
    private Object value;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}