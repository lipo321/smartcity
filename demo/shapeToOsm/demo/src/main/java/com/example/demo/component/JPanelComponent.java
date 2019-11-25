//package com.shape.tool.view.component;

package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;
import com.example.demo.dict.ComponentTypeEnum;

import javax.swing.*;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JPanelComponent extends AbstractDefaultComponent {

    public static JPanel initPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBorder(ComponentStyle.DEFAULT_BORDER);
        jPanel.setFont(ComponentStyle.DEFAULT_FONT);
        return jPanel;
    }

    public static JPanel createPanel() {
        JPanel jPanel = initPanel();

        return jPanel;
    }

    public static JPanel createPanel(ComponentTypeEnum componentTypeEnum) {
        JPanel jPanel = initPanel();
        int x = 0;
        switch (componentTypeEnum) {
            case OBJECT:
                x = CONTENT_WIDTH;
                break;
            case DOMAIN:
                x = CONTENT_WIDTH;
                break;
            case DATA_TYPE:
                x = ComponentStyle.BORDER_DISTANCE + contentPanelWidth(2);
                break;
            case OBJECT_TYPE:
                x = ComponentStyle.BORDER_DISTANCE + contentPanelWidth(2);
                break;
            default:
                break;
        }
        jPanel.setLocation(x, CONTENT_HEIGHT);
        jPanel.setSize(contentPanelWidth(2), columnHeight(6));
        wrapContentHeight(ComponentStyle.TEXT_HEIGHT);
        wrapContentWidth(ComponentStyle.BORDER_DISTANCE);
        return jPanel;
    }
}
