package com.example.demo.tools;

import com.example.demo.dict.GeomType;
import com.example.demo.entity.*;
import com.example.demo.shapeEntity.ShapeFieldInfo;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;

import java.util.HashMap;
import java.util.List;

/**
 * 将几何shape要素转换成OsmObject
 *
 * @author lipo@126.com
 * @version 2019年7月4日
 */
public class ShapeToOsmObjectUtils {

    private static final int                   PRECISION = (int) 1e10;
    static               HashMap<String, Long> coordinatesHash;

    /**
     * 将坐标点转换成hash表的key值
     *
     * @param coordinated 传入坐标点
     * @return String 拼接完成后转换成String类型
     */
    public static String getHashKey(Coordinate coordinated) {
        String ret = String.valueOf((int) (coordinated.x * PRECISION));
        ret += String.valueOf((int) (coordinated.y * PRECISION));
        return ret;
    }

    /**
     * 根据坐标点获取node的唯一id；存在，获取；不存在，新生成。
     *
     * @param coordinated 坐标点
     * @return Long nodeID
     */
    public static Long getNodeId(Coordinate coordinated) {
        String hashKey = getHashKey(coordinated);
        boolean isContain = coordinatesHash.containsKey(coordinated);
        if (true == isContain) {
            return coordinatesHash.get(coordinated);
        }

        //后期应该传输工作机器id和数据id
        Long hashId = IdUtils.getId(1, 0);
        coordinatesHash.put(hashKey, hashId);

        return hashId;
    }

    /**
     * 将每个要素转换成对象信息
     *
     * @param feature
     * @param shapeFieldsInfo
     * @return
     */
    public static OsmObject featureToObject(SimpleFeature feature, List<ShapeFieldInfo> shapeFieldsInfo) {

        OsmObject object = new OsmObject();

        //1.将type信息赋值给对象
        addObjectTypeToOsmObject(object);

        //2.实体信息赋值到对象
        addEntityToOsmObject(object, feature);

        //3.将属性信息赋值给对象的tags
        addTagsToOsmObject(object, feature, shapeFieldsInfo);

        return object;
    }


    public static void addEntityToOsmObject(OsmObject object, SimpleFeature feature) {

        OsmEntity entity = new OsmEntity();

        //获取要素的几何信息
        Geometry geo = (Geometry) feature.getDefaultGeometry();

        //一个geometry可能含有n个geometry
        int geomNum = geo.getNumGeometries();

        //遍历每个几何信息，并打印点位信息
        for (int i = 0; i < geomNum; i++) {
            //获取其中每一个geometry
            Geometry singleGeo = geo.getGeometryN(i);
            String geoTypeName = singleGeo.getGeometryType();
            switch (geoTypeName) {
                case "Point":
                    pointToEntity(singleGeo, entity);
                    break;
                case "LineString":
                    lineStringToEntity(singleGeo, entity);
                    break;

                default:
                    return;
            }
        }
        object.setEntity(entity);
    }

    public static void lineStringToEntity(Geometry singleGeo, OsmEntity entity) {
        entity.setType(GeomType.WAY);
        OsmWay way = new OsmWay();
        int pointCount = singleGeo.getNumPoints();
        Coordinate[] coords = singleGeo.getCoordinates();
        for (int i = 0; i < pointCount; i++) {
            OsmNode node = new OsmNode();
            node.setX(coords[i].x);
            node.setY(coords[i].y);
            node.setZ(0.0);
            way.getNodes().add(node);
        }
        entity.setNodes(way);
    }

    /**
     * 点类型转换成OsmEntity
     *
     * @param singleGeo
     * @param entity
     */
    public static void pointToEntity(Geometry singleGeo, OsmEntity entity) {
        entity.setType(GeomType.NODE);
        OsmNode node = new OsmNode();
        node.setType("NODE");
        Coordinate[] coords = singleGeo.getCoordinates();
        node.setX(coords[0].x);
        node.setY(coords[0].y);
        node.setZ(0.0);
        entity.setNodes(node);
    }

    /**
     * 将对象类型赋值给对象
     *
     * @param object
     */
    public static void addObjectTypeToOsmObject(OsmObject object) {
        ObejctType oType = new ObejctType();
        String oTypeName = "矢量对象";
        oType.setName(oTypeName);
        oType.setOtid(123L);

        object.setType(oType);
    }

    /**
     * 将要素的属性信息赋值给对象
     *
     * @param object
     * @param feature
     * @param shapeFieldsInfo
     */
    public static void addTagsToOsmObject(OsmObject object, SimpleFeature feature, List<ShapeFieldInfo> shapeFieldsInfo) {
        int attributesCount = feature.getAttributeCount();
        List<Object> attributes = feature.getAttributes();
        for (int i = 1; i < attributesCount; i++) {
            object.getTags().put(shapeFieldsInfo.get(i).getFieldName(), attributes.get(i).toString());
        }

    }
}
