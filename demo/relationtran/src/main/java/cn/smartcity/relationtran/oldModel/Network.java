

package cn.smartcity.relationtran.oldModel;

import java.util.ArrayList;
import java.util.List;


/**
 * 关系
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class Network {


    /**
     * 记录关系的目标结点
     */
    private List<RNode> nodes = new ArrayList<RNode>();

    public Network() {

    }

    /**
     * 如果用户获取List后采用iterator或Stream遍历时，需要自己进行同步操作，例如：
     * {@code
     * synchronized(list) {
     * Iterator it = list.iterator();
     * while(it.hasNext()) {
     * doSomething(it.next());
     * }
     * ...
     * }}
     *
     * @return
     */
    public List<RNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<RNode> nodes) {
        this.nodes.clear();
        this.nodes.addAll(nodes);
    }

    public void addNode(RNode rNode) {
        this.nodes.add(rNode);
    }

}
