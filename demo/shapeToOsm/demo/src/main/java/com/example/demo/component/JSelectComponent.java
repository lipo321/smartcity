//package com.shape.tool.view.component;

package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;
import com.example.demo.dict.SelectTypeEnum;

import javax.swing.*;
import java.awt.*;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JSelectComponent extends AbstractDefaultComponent {
    public static JComboBox initSelect(SelectTypeEnum selectTypeEnum) {
        JComboBox jComboBox = new JComboBox<>(checkType(selectTypeEnum));
        jComboBox.setMaximumRowCount(3);
//        jComboBox.setSize(10,10);
//        jComboBox.setFont(ComponentStyle.DEFAULT_FONT);
//        jComboBox.setBorder(ComponentStyle.DEFAULT_BORDER);
//        jComboBox.setBackground(new Color(229, 229, 229));
        return jComboBox;
    }

    public static JComboBox createSelect(SelectTypeEnum selectTypeEnum) {
        JComboBox jComboBox = initSelect(selectTypeEnum);
        return jComboBox;
    }


    public static String[] checkType(SelectTypeEnum selectTypeEnum) {
        switch (selectTypeEnum) {
            case FIELD:
                return OBJECT_FIELD;
            case ENCODING:
                return FILE_ENCODING;
            case SYSTEM:
                return COORDINATE_SYSTEM;
            default:
                return OBJECT_FIELD;
        }
    }
}
