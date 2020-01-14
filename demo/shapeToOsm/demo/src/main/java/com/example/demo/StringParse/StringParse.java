package com.example.demo.StringParse;

import com.example.demo.test.GxBBox;

import java.security.PublicKey;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class StringParse {

    public static void main(String[] args) {
        String bbox = "BboX3D(1 2 3,4 5 6)";
        boolean isStart = bbox.toUpperCase().startsWith("BBOX3D");
        if (isStart) {
            int pos = bbox.indexOf("(");
            double minx = Double.parseDouble(String.valueOf(bbox.charAt(pos + 1)));
            double miny = Double.parseDouble(String.valueOf(bbox.charAt(pos + 3)));
            double minz = Double.parseDouble(String.valueOf(bbox.charAt(pos + 5)));
            double maxx = Double.parseDouble(String.valueOf(bbox.charAt(pos + 7)));
            double maxy = Double.parseDouble(String.valueOf(bbox.charAt(pos + 9)));
            double maxz = Double.parseDouble(String.valueOf(bbox.charAt(pos + 11)));
            double temp_minx = Double.parseDouble("23");
            GxBBox gxBBox = new GxBBox(minx, miny, minz, maxx, maxy, maxz);

            System.out.println(gxBBox.toWKT());
        }
        System.out.print("2  2  2".trim());

    }
}
