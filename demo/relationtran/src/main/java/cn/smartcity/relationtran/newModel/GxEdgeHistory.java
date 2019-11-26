package cn.smartcity.relationtran.newModel;

import java.util.List;

/**
 * <p>GxEdgeHistory</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 16:20 2019/10/28
 */
public class GxEdgeHistory {
    /**
     * 关系id
     */
    private Long rid;
    /**
     * 关系类id
     */
    private Long typeId;
    /**
     * 关系类名称
     */
    private String typeName;

    /**
     * 相同一条关系的变化历史
     */
    private List<GxEdge> histories;

    public GxEdgeHistory() {
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<GxEdge> getHistories() {
        return histories;
    }

    public void setHistories(List<GxEdge> histories) {
        this.histories = histories;
    }
}
