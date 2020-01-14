package cn.smartcity.relationtran.newModel;

import java.util.List;

/**
 * <p>GxVersion</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 16:18 2019/10/28
 */
public class GxVersion {
    private GxNode              from;
    private GxNode              to;
    /**
     * <关系id，关系历史>
     */
    private List<GxEdgeHistory> relations;

    public GxVersion() {
    }

    public GxNode getFrom() {
        return from;
    }

    public void setFrom(GxNode from) {
        this.from = from;
    }

    public GxNode getTo() {
        return to;
    }

    public void setTo(GxNode to) {
        this.to = to;
    }

    public List<GxEdgeHistory> getRelations() {
        return relations;
    }

    public void setRelations(List<GxEdgeHistory> relations) {
        this.relations = relations;
    }
}
