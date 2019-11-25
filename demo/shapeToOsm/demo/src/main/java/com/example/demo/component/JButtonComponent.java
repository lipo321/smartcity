package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JButtonComponent extends AbstractDefaultComponent {

    public static JButton createButton(String title) {
        JButton jButton = new JButton(title);
        jButton.setFont(ComponentStyle.DEFAULT_FONT);
        return jButton;
    }

    public static JButton createSelectButton(String title, JTextField jTextField) {
        JButton jButton = createButton(title);
        jButton.addActionListener(event -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showDialog(new JLabel(), "输出目录");
            File file = chooser.getSelectedFile();
            if (file != null) {
                jTextField.setText(file.getAbsoluteFile().toString());
            }
        });
        return jButton;
    }
}
