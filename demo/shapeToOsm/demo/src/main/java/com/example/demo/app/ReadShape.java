package com.example.demo.app;


import com.example.demo.entity.OsmObject;
import com.example.demo.shapeEntity.ShapeFieldInfo;
import com.example.demo.tools.JsonUtils;
import com.example.demo.tools.ShapeToOsmObjectUtils;
import com.example.demo.tools.ShapeUtils;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;


import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用GeoTools工具包，读取shape文件要素及属性信息
 *
 * @author lipo@126.com
 * @date 2019年7月3日
 */
public class ReadShape {

    public static void main(String[] args) throws Exception {

        //1.初始化调用界面
//        AppTool appTool =  new AppTool();
//        appTool.initJFrame();

        List<OsmObject> objects = new ArrayList<>();

        //2.数据源选择shp扩展类型文件
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (null == file) {
            return;
        }

        //3.构建ShapeDataStore对象
        ShapefileDataStore source = ShapeUtils.buildDataStore(file.getPath());

        //4.获取Shape文件的坐标系信息,WKT格式
        String coordinateSystemWkt = ShapeUtils.getCoordinateSystemWKT(file.getPath());

        //5.获取shape文件的字段信息
        List<ShapeFieldInfo> shapeFieldsInfo = ShapeUtils.distillShapeFieldInfo(file.getPath());

        //6.获取参照信息并进行强制转换,目前全部转换成WGS84
        String epsgWkt = "EPSG:4326";
        ShapeUtils.TransToTargetCRS(source, epsgWkt);

        //7.获取shape图层名称，并获取所有要素集合
        String layerName = source.getTypeNames()[0];
        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = source
                .getFeatureSource(layerName);
        FeatureCollection<SimpleFeatureType, SimpleFeature> result = featureSource.getFeatures();

        //8.遍历所有要素并获取几何和属性信息,并转换成交换格式对象OsmObject
        FeatureIterator<SimpleFeature> features = result.features();
        while (features.hasNext()) {
            SimpleFeature feature = features.next();
            objects.add(ShapeToOsmObjectUtils.featureToObject(feature, shapeFieldsInfo));
        }

        //9.将所有对象输出到指定的JSON文件中
        JFileChooser outFileChooser = new JFileChooser();
        int returnVal;
        do {
            returnVal = outFileChooser.showOpenDialog(outFileChooser);
        } while (JFileChooser.APPROVE_OPTION != returnVal);

        String outFilePath = outFileChooser.getSelectedFile().toPath().toString();
        File outPath = new File(outFilePath);
        JsonUtils.ObjectsToJson(objects, outPath);
    }
}
