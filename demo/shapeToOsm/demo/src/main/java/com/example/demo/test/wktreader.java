package com.example.demo.test;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.coordinate.PolyhedralSurface;
import org.opengis.geometry.coordinate.Tin;
/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class wktreader {

    private GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

    public void createPolygonByWKT() throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
        Polygon polygon = (Polygon) reader.read("POLYGON((20 10,30 0,40 10,30 20,20 10))");
        String text = polygon.getBoundary().getEnvelope().toText();
        double minx = polygon.getEnvelopeInternal().getMinX();
        double miny = polygon.getEnvelopeInternal().getMinY();
        double maxx = polygon.getEnvelopeInternal().getMaxX();
        double maxy = polygon.getEnvelopeInternal().getMaxY();

        System.out.println(minx);
        System.out.println(miny);
        System.out.println(maxx);
        System.out.println(maxy);
        System.out.println("-------------------------");
        System.out.println(text);
    }

    public void createMultiPolygon() throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
        MultiPolygon multiPolygon = (MultiPolygon) reader.read("MULTIPOLYGON( ((0 0 0, 0 0 1, 0 1 1, 0 1 0, 0 0 0))," +
                "((0 0 0, 0 1 0, 1 1 0, 1 0 0, 0 0 0))," +
                " ((0 0 0, 1 0 0, 1 0 1, 0 0 1, 0 0 0))," +
                " ((1 1 0, 1 1 1, 1 0 1, 1 0 0, 1 1 0))," +
                " ((0 1 0, 0 1 1, 1 1 1, 1 1 0, 0 1 0)), " +
                "((0 0 1, 1 0 1, 1 1 1, 0 1 1, 0 0 1)))");
        Integer dim = multiPolygon.getDimension();


        double minx = multiPolygon.getEnvelopeInternal().getMinX();
        double miny = multiPolygon.getEnvelopeInternal().getMinY();
        double maxx = multiPolygon.getEnvelopeInternal().getMaxX();
        double maxy = multiPolygon.getEnvelopeInternal().getMaxY();
        System.out.println(dim);
    }

    public void createPolyheral() throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
//        Tin polyhedralSurface =(Tin) reader.read(
//                "MULTIPOLYGON( ((0 0 0, 0 0 1, 0 1 1, 0 1 0, 0 0 0))," +
//                        " ((0 0 0, 0 1 0, 1 1 0, 1 0 0, 0 0 0)), ((0 0 0, 1 0 0, 1 0 1, 0 0 1, 0 0 0))," +
//                        " ((1 1 0, 1 1 1, 1 0 1, 1 0 0, 1 1 0)), ((0 1 0, 0 1 1, 1 1 1, 1 1 0, 0 1 0))," +
//                        " ((0 0 1, 1 0 1, 1 1 1, 0 1 1. 0 0 1)) )");
        Tin polyhedralSurface = (Tin) reader.read(" Tin Z( ((0 0 0, 0 0 1, 0 1 0, 0 0 0)), " +
                "((0 0 0, 0 1 0, 1 0 0, 0 0 0)), " +
                "((0 0 0, 1 0 0, 0 0 1, 0 0 0)), ((1 0 0, 0 1 0, 0 0 1, 1 0 0))");
        String p_str = polyhedralSurface.getControlPoints().toString();
        System.out.println(p_str);
    }

    public static void main(String[] args) throws ParseException {
        wktreader reader = new wktreader();
        reader.createPolygonByWKT();
        reader.createMultiPolygon();
//       reader.createPolyheral();
    }
}
