package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;

import javax.swing.*;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JInputComponent extends AbstractDefaultComponent {
    public static JTextField initInput() {
        JTextField jTextField = new JTextField();
        jTextField.setBorder(ComponentStyle.DEFAULT_BORDER);
        jTextField.setFont(ComponentStyle.DEFAULT_FONT);
        return jTextField;
    }

    public static JTextField createInput() {
        JTextField jTextField = initInput();
        return jTextField;
    }

    public static JTextField createUserInput() {
        JTextField jTextField = initInput();
        configField(jTextField);
        return jTextField;
    }

    public static JPasswordField createPasswordInput() {
        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setBorder(ComponentStyle.DEFAULT_BORDER);
        jPasswordField.setFont(ComponentStyle.DEFAULT_FONT);
        configField(jPasswordField);
        return jPasswordField;
    }

    private static JTextField configField(JTextField jTextField) {
        return jTextField;
    }


}
