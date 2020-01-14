package com.example.demo.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 将几何shape要素转换成OsmObject
 *
 * @author lipo@126.com
 * @version 2019年7月4日
 */
public class MyFrame02 extends JFrame {

    public static void main(String[] args) throws Exception {
        new MyFrame02();
    }

    JTextField     txtName     = new JTextField();
    JPasswordField txtPassWord = new JPasswordField();

    JButton loginButton = new JButton("登录");
    JButton closeButton = new JButton("关闭");

    //构造无参构造函数
    public MyFrame02() {
        setBounds(25, 25, 250, 250);
        Container container = getContentPane();
        GridLayout layout = new GridLayout(4, 1);
        container.setLayout(layout);

//        Dimension preferredSize = new Dimension(10,10);
//        LoginButton.setMaximumSize(preferredSize );
//        CloseButton.setMaximumSize(preferredSize );
//        txtPassWord.setMaximumSize(preferredSize);
//        txtName.setMaximumSize(preferredSize);

        closeButton.setLocation(10, 10);
        //  container.add(new JLabel("用户名"));
        container.add(txtName);
        //container.add(new JLabel("密码"));
        container.add(txtPassWord);
        container.add(loginButton);
        container.add(closeButton);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String passWord = new String(txtPassWord.getPassword());

                if (name.equals("tom") && passWord.equals("123")) {
                    System.out.println("登录成功");
                } else {
                    System.out.println("登录失败");
                    System.exit(0);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }


}


