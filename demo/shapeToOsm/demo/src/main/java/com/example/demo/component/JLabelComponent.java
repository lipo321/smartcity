//package com.shape.tool.view.component;

package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;

import javax.swing.*;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JLabelComponent extends AbstractDefaultComponent {
    public static JLabel initLabel(String title) {
        JLabel jLabel = new JLabel(title);
        jLabel.setFont(ComponentStyle.DEFAULT_FONT);

        return jLabel;
    }


    public static JLabel createLabel(String title) {
        JLabel jLabel = initLabel(title);
        //jLabel.setSize(fontWidth(title.length()), ComponentStyle.TEXT_HEIGHT);
        return jLabel;
    }

}
