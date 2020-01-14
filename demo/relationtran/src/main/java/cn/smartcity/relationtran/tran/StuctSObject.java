package cn.smartcity.relationtran.tran;

import cn.smartcity.relationtran.oldModel.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lipoGiser
 * @date 2019/11/26 15:55
 * @Version 0.1
 */
public class StuctSObject {

    public List<SObject> organizeSObject() {
        //1.创建具有关系的多粒度时空对象数据
        List<SObject> sObjects = new ArrayList<>();

        SObject sObject = new SObject();
        structOneSObject(sObject);
        sObjects.add(sObject);

        return sObjects;
    }

    private void structOneSObject(SObject sObject) {
        //1.设置对象id
        sObject.setId(1L);
        //2.设置对象的真实时间
        sObject.setRealtime((new Date()).getTime());
        //3.设置时空域Id
        sObject.setSdomainId(234L);
        //4.设置对象类模板
        OType oType = new OType();
        oType.setId(4000L);
        oType.setName("湖人球员");
        sObject.setoType(oType);
        //5.设置对象的关系网络
        Network network = new Network();
        structNetwork(network);
        sObject.setNetwork(network);
    }

    private void structNetwork(Network network) {
        List<RNode> nodes = new ArrayList<>();
        RNode node = new RNode();
        structNode(node);
        nodes.add(node);
        network.setNodes(nodes);
    }

    private void structNode(RNode node) {
        //1.设置节点的id
        node.setId(11L);
        //2.设置关联对象的id
        node.setRelatedObjectId(2L);
        //3.设置关联对象的类模板
        OType oType = new OType();
        oType.setId(4001L);
        oType.setName("篮网球员");
        node.setoType(oType);
        //4.增加边
        REdge edge = new REdge();
        structEdge(edge);
        node.setEdge(edge);
    }

    private void structEdge(REdge edge) {
        //1.设置边的id
        edge.setId(111L);
        //2.设置边的强度
        edge.setIntensity(34);
        //3.设置边的规则,只有基类
        List<ARule> rules = new ArrayList<>();
        //4.设置关系类
        Relation relation = new Relation();
        relation.setId(1111L);
        relation.setName("关系类");
        edge.setRelation(relation);
    }
}
