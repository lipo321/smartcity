package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
@Data
public abstract class AbstractJFrameComponent extends AbstractDefaultComponent {

    public final JFrame jFrame = new JFrame();

    public void initJFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setBounds(((int) dimension.getWidth() - ComponentStyle.FRAME_WIDTH) / 2,
                ((int) dimension.getHeight() - ComponentStyle.FRAME_HEIGHT) / 2,
                ComponentStyle.FRAME_WIDTH / 2, ComponentStyle.FRAME_HEIGHT / 2);

        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        initContent();
    }

    /**
     * 初始化对话框中的控件内容
     */
    public abstract void initContent();

}
