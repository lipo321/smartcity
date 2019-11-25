//package com.shape.tool.view.component;
package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;


/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
abstract class AbstractDefaultComponent {
    public static int CURRENT_HEIGHT = 0;
    public static int CONTENT_WIDTH = 0;
    public static int CONTENT_HEIGHT = 0;
    public static int CURRENT_PAGE = 1;
    public static int TOTAL_PAGE = 0;

    public static String[] COLUMN_NAME = {"序号", "", ""};
    public static String[][] ROW_DATA = {
            {"1", "", ""},
            {"2", "", ""},
            {"3", "", ""},
            {"4", "", ""},
            {"5", "", ""},
            {"6", "", ""},
            {"7", "", ""},
            {"8", "", ""},
            {"9", "", ""},
            {"10", "", ""}
    };

    public static String[] FILE_ENCODING = {"ANSI", "UTF-8", "UTF-16", "UTF-32", "GBK", "GBK2312", "US-ASCII", "ISO-8859-1"};
    public static String[] OBJECT_FIELD = {""};
    public static String[] COORDINATE_SYSTEM = {"WGS84"};

    public static final int DEFAULT_TEXTAREA = inputWidth(2, 2) - buttonWidth(2);

    public static int wrapCurrentHeight(int height) {
        CURRENT_HEIGHT += height;
        return height;
    }

    public static int wrapContentWidth(int width) {
        CONTENT_WIDTH += width;
        return width;
    }

    public static int wrapContentHeight(int height) {
        CONTENT_HEIGHT += height;
        return height;
    }

    public static int fontWidth(int letter) {
        return ComponentStyle.DEFAULT_FONT_SIZE * (letter + 1);
    }

    public static int buttonWidth(int letter) {
        return ComponentStyle.DEFAULT_FONT_SIZE * (letter + 1) + ComponentStyle.CONTENT_BORDER_DISTANCE * 2;
    }

    public static int columnHeight(int row) {
        return ComponentStyle.CONTENT_BORDER_DISTANCE * (row + 1) + ComponentStyle.TEXT_HEIGHT * row;
    }

    public static int inputWidth(int inputRow, int textRow) {
        return (ComponentStyle.FRAME_WIDTH -
                (ComponentStyle.COLUMN_SPACING * (textRow + 1) + ComponentStyle.TEXT_HEIGHT * textRow)
                - ComponentStyle.BORDER_LETTER * (inputRow + textRow + 2)) / inputRow;
    }

    public static void resetContentWidth() {
        CONTENT_WIDTH = 0;
    }

    public static void resetContentHeight() {
        CONTENT_HEIGHT = 0;
    }

    public static void resetContent() {
        resetContentWidth();
        resetContentHeight();
    }

    public static int contentPanelWidth(int row) {
        return (ComponentStyle.FRAME_WIDTH - ((row > 1 ? row : 1) + 1) * ComponentStyle.BORDER_DISTANCE - 5)
                / (row > 1 ? row : 1);
    }
}
