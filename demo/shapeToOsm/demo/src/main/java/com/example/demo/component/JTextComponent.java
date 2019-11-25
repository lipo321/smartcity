//package com.shape.tool.view.component;
package com.example.demo.component;


import com.example.demo.component.style.ComponentStyle;

import javax.swing.*;
import java.awt.*;

//DefaultComponent

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JTextComponent extends AbstractDefaultComponent {
    public static JTextArea initText() {
        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setFont(ComponentStyle.DEFAULT_FONT);
        jTextArea.setBackground(Color.WHITE);
        jTextArea.setBorder(ComponentStyle.DEFAULT_BORDER);
        return jTextArea;
    }

    public static JTextArea createText(JPanel jPanel) {
        JTextArea jTextArea = initText();
        jPanel.add(jTextArea);
        return jTextArea;
    }
}
