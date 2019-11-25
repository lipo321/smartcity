package com.example.demo.tools;

import com.example.demo.shapeEntity.ShapeFieldInfo;
import com.vividsolutions.jts.geom.Coordinate;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * shape文件操作公共类
 *
 * @author lipo@126.com
 * @version 2019年7月4日
 */
public class ShapeUtils {
    /**
     * 获取Shape文件的坐标系信息
     *
     * @param shpFilePath shp文件路径
     * @return 坐标系的WKT形式
     */
    public static String getCoordinateSystemWKT(String shpFilePath) {
        ShapefileDataStore dataStore = buildDataStore(shpFilePath);
        try {
            return dataStore.getSchema().getCoordinateReferenceSystem().toWKT();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 构建ShapeDataStore对象
     *
     * @param shpFilePath shape文件路径
     * @return
     */
    public static ShapefileDataStore buildDataStore(String shpFilePath) {
        ShapefileDataStoreFactory factory = new ShapefileDataStoreFactory();
        try {
            ShapefileDataStore dataStore = (ShapefileDataStore) factory
                    .createDataStore(new File(shpFilePath).toURI().toURL());
            if (null != dataStore) {
                dataStore.setCharset(Charset.forName("UTF-8"));
            }
            return dataStore;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 投影转换，lon经度，lat纬度，EPSG格式：EPSG4610或者wkt格式
     *
     * @param lon
     * @param lat
     * @param epsgSource 源坐标系
     * @param epsgTarget 目标坐标系
     */
    public static Coordinate projectTransform(double lon, double lat,
                                              String epsgSource,
                                              String epsgTarget)
            throws FactoryException, NoSuchAuthorityCodeException, TransformException {
        Coordinate sourcePoint = new Coordinate(lon, lat);

        //定义转换前和转换后的投影，可以用ESPG或者wkt
        CoordinateReferenceSystem crsSource = CRS.decode(epsgSource);
        CoordinateReferenceSystem crsTarget = CRS.decode(epsgTarget);

        //投影转换
        MathTransform transform = CRS.findMathTransform(crsSource, crsTarget);
        Coordinate pointTarget = new Coordinate();
        JTS.transform(sourcePoint, pointTarget, transform);

        return pointTarget;
    }

    /**
     * 将整个数据源转换成指定坐标系
     *
     * @param store      数据源
     * @param epsgTarget 目标坐标系
     */
    public static void TransToTargetCRS(ShapefileDataStore store, String epsgTarget) {

        try {
            CoordinateReferenceSystem crs = CRS.decode(epsgTarget);
            store.forceSchemaCRS(crs);
        } catch (NoSuchAuthorityCodeException e) {
            e.printStackTrace();
        } catch (FactoryException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提取shape文件包含的属性字段名称和类型信息
     *
     * @param shpFilePath shape文件路径
     * @return 属性信息
     */
    public static List<ShapeFieldInfo> distillShapeFieldInfo(String shpFilePath) {
        List<ShapeFieldInfo> fieldList = new ArrayList<>();

        ShapefileDataStore dataStore = buildDataStore(shpFilePath);

        try {
            List<AttributeDescriptor> attributeDescriptorList =
                    dataStore.getFeatureSource().getSchema().getAttributeDescriptors();
            for (AttributeDescriptor attr : attributeDescriptorList) {
                ShapeFieldInfo field = new ShapeFieldInfo();
                field.setFieldName(attr.getLocalName());
                field.setFieldType(attr.getType().getBinding());
                fieldList.add(field);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dataStore.dispose();
        }
        return fieldList;
    }


}
