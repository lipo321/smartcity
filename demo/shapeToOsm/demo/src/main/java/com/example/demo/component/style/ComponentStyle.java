package com.example.demo.component.style;

import lombok.Data;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * 将几何shape要素转换成OsmObject
 *
 * @author lipo@126.com
 * @version 2019年7月4日
 */
public class ComponentStyle {
    /**
     * 对话框宽度
     */
    public static int FRAME_WIDTH     = 900;
    /**
     * 对话框高度
     */
    public static int FRAME_HEIGHT    = 930;
    /**
     * 边框
     */
    public static int BORDER_LETTER   = 10;
    /**
     * 边框距离
     */
    public static int BORDER_DISTANCE = 15;

    public static int    TABLE_DISTANCE_WIDTH    = 30;
    public static int    TABLE_DISTANCE_HEIGHT   = 38;
    public static int    BORDER_SPACING          = 5;
    public static int    COLUMN_SPACING          = 15;
    public static int    ROW_SPACING             = 15;
    public static int    CONTENT_BORDER_DISTANCE = 10;
    public static int    DEFAULT_FONT_SIZE       = 14;
    public static Font   DEFAULT_FONT            = new Font("微软雅黑", Font.PLAIN, DEFAULT_FONT_SIZE);
    public static Color  DEFAULT_COLOR           = new Color(206, 206, 206);
    public static Color  DEFAULT_BG_COLOR        = new Color(229, 229, 229);
    public static Color  DEFAULT_FG_COLOR        = new Color(165, 186, 229);
    public static Border DEFAULT_BORDER          = new LineBorder(DEFAULT_COLOR);
    public static int    WRAP_BORDER_DISTANCE    = BORDER_LETTER + BORDER_DISTANCE;
    public static int    TEXT_HEIGHT             = 30;
    public static int    INPUT_HEIGHT            = 25;
    public static int    BUTTON_HEIGHT           = 25;
    public static int    BUTTON_WIDTH            = 50;
    public static int    SELECT_HEIGHT           = 25;

}
