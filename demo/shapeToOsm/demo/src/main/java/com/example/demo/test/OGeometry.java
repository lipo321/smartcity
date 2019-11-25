package com.example.demo.test;

import com.vividsolutions.jts.geom.Geometry;
import onegis.psde.form.Form;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public abstract class OGeometry extends Geometry {

    private static final long serialVersionUID = 8863622679187376702L;

    // 以下这些是JTS内置SORTINDEX类型
    // static final int SORTINDEX_POINT = 0;
    // static final int SORTINDEX_MULTIPOINT = 1;
    // static final int SORTINDEX_LINESTRING = 2;
    // static final int SORTINDEX_LINEARRING = 3;
    // static final int SORTINDEX_MULTILINESTRING = 4;
    // static final int SORTINDEX_POLYGON = 5;
    // static final int SORTINDEX_MULTIPOLYGON = 6;
    // static final int SORTINDEX_GEOMETRYCOLLECTION = 7;

    /**
     * 扩展SORTINDEX类型,从20以后进行扩展
     */
    public static final int SORTINDEX_EXT = 20;

    public static final int SORTINDEX_EXT_NODE = 21;
    public static final int SORTINDEX_EXT_WAY = 22;
    public static final int SORTINDEX_EXT_RELATION = 23;
    public static final int SORTINDEX_EXT_CLOSEDWAY = 24;

    public static final int SORTINDEX_BBOX = 30;

    /**
     * @roseuid 5A2B66150331
     * @since 1.0.0
     */
    public OGeometry() {

        super(Form.GEOMETRY_FACTORY);
    }

    /**
     * @return String
     * @roseuid 5A2B5D7C0044
     */
    @Override
    public String toText() {
        return toWKT();
    }

    /**
     * 返回几何的WKT描述字符串
     *
     * @return String
     * @roseuid 5A2B5D5C0081
     */
    public abstract String toWKT();

    /**
     * 根据wkt设置
     *
     * @param wkt wkt字符串
     * @roseuid 5A2B568200CA
     */
    public abstract void fromWkt(String wkt);
}