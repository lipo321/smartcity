package cn.smartcity.relationtran.tran;

import cn.smartcity.relationtran.newModel.*;
import cn.smartcity.relationtran.oldModel.RNode;
import cn.smartcity.relationtran.oldModel.SObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lipoGiser
 * @date 2019/11/26 15:56
 * @Version 0.1
 * @des 由于时间问题就不做相关的非空判断啦
 */
public class StructNetwork {
    public GxNetwork getNetwork(List<SObject> sObjects) {
        GxNetwork network = new GxNetwork();
        //1.姑且认为sobject属于一个时空域
        if (sObjects.size() != 0 && sObjects != null) {
            network.setPrjId(sObjects.get(0).getSdomainId());
        }
        //2.组织关系网
        List<GxVersion> versions = new ArrayList<>();
        for (SObject object : sObjects) {
            //2.1获取该对象下所有关系
            List<RNode> nodes = object.getNetwork().getNodes();
            for (RNode node : nodes) {
                GxVersion gxVersion = new GxVersion();
                structVersion(gxVersion, node, object);
                versions.add(gxVersion);
            }
        }
        network.setVersions(versions);

        return network;
    }

    private void structVersion(GxVersion gxVersion, RNode node, SObject object) {
        //1.组织开始节点
        GxNode from = new GxNode();
        structFromNode(from, object);
        gxVersion.setFrom(from);

        //2.组织结束节点,工程id先不设置一样的
        GxNode to = new GxNode();
        to.setOid(node.getRelatedObjectId());
        to.setClsId(node.getoType().getId());
        to.setClsName(node.getoType().getName());
        gxVersion.setTo(to);

        //3.设置关系及关系历史
        List<GxEdgeHistory> gxEdgeHistories = new ArrayList<>();
        GxEdgeHistory edgeHistory = new GxEdgeHistory();

        //3.1设置边的id,是否是这个有待商榷，因为这个是实例化的边
        edgeHistory.setRid(node.getEdge().getId());
        //3.2设置边的关系类
        edgeHistory.setTypeId(node.getEdge().getRelation().getId());
        edgeHistory.setTypeName(node.getEdge().getRelation().getName());

        //3.3设置该id关系的变化历史
        List<GxEdge> edges = new ArrayList<>();
        GxEdge gxEdge = new GxEdge();
        //3.3.1 设置一个边历史的更新时间
        gxEdge.setuTime(object.getRealtime());
        //3.3.2 设置关系状态，配合对象Action确定对象关系的创建、消亡
        //例如：1、关系修改等确定关系的id，增加关系
        //例如：2、对象的创建，增加关系
        //例如：3、对象的消亡，是怎么处理关系，删除还是将消亡时间赋给跟该对象有关系的所有关系的断开时间
        gxEdge.setState(EGxRelationState.KEEP.getCode());
        //3.3.3 设置关系强度
        gxEdge.setIntensity((double) node.getEdge().getIntensity());
        //3.3.4 设置关系规则
        //node.getEdge().getRules();

        //3.3.5 设置关系属性
        //node.getEdge().getProperties();
        edges.add(gxEdge);
        edgeHistory.setHistories(edges);

        gxEdgeHistories.add(edgeHistory);
        gxVersion.setRelations(gxEdgeHistories);
    }

    private void structFromNode(GxNode from, SObject object) {
        //1.1开始节点id
        from.setOid(object.getId());
        //1.2开始节点工程id
        from.setPrjId(object.getSdomainId());
        //1.3开始节点类模板id
        from.setClsId(object.getoType().getId());
        //1.4开始节点类模板名称
        from.setClsName(object.getoType().getName());
        //1.5节点与对象的属性转换目前不做
    }
}
