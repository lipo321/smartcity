package cn.smartcity.relationtran.oldModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 连接集合
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史： 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public class Connectors {

    private List<Connector> connectors;

    @JsonIgnore
    private List<Long> connectorIds;

    public Connectors() {
        connectors = new ArrayList<>();
    }

    public Connectors(List<Long> connectorIds) {

        List<Connector> result = new ArrayList<>();
        for (long id : connectorIds) {
            result.add(new Connector(id));
        }
        this.connectors = result;
    }

    /**
     * 根据集合列表构建连接关系
     *
     * @param connectorList 关系列表
     * @return Connectors
     */
    public static Connectors buildConnectors(List<Connector> connectorList) {
        Connectors connectors = new Connectors();
        connectors.setConnectors(connectorList);
        return connectors;
    }

    public List<Connector> getConnectors() {
        return connectors;
    }

    public void setConnectors(List<Connector> connectors) {
        this.connectors = connectors;
    }

    public List<Long> getConnectorIds() {
        return connectorIds;
    }

    public void setConnectorIds(List<Long> connectorIds) {
        this.connectorIds = connectorIds;
    }

    public void setConnectorIds() {
        this.connectorIds = toList();
    }

    public void addConnectors(List<Connector> connectors) {
        for (Connector connector : connectors) {
            if (!contain(connector)) {
                this.connectors.add(connector);
            }
        }
    }

    private boolean contain(Connector connector) {
        for (Connector connector1 : connectors) {
            if (connector1.getId().equals(connector.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 將字段集合转变成字段ID列表
     *
     * @return List<Long>
     */
    public List<Long> toList() {
        return this.connectors.stream().map(f -> f.getId()).collect(Collectors.toList());
    }

    public void addConnector(Connector connector) {
        this.connectors.add(connector);
    }

    /**
     * 將字段集合转变成字符串
     *
     * @return String
     */
    @Override
    public String toString() {
        String result = "[]";
        if (connectors != null) {
            List<Long> connectorIdList = this.connectors.stream().map(f -> f.getId()).collect(Collectors.toList());
            result = connectorIdList.toString();
        }
        return result;
    }
}
