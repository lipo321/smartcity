package com.example.demo.app;


import com.example.demo.component.style.ComponentStyle;
import com.example.demo.dict.ComponentTypeEnum;
import com.example.demo.dict.SelectTypeEnum;
import com.example.demo.component.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 将几何shape要素转换成OsmObject
 *
 * @author lipo@126.com
 * @version 2019年7月4日
 */
public class AppTool extends AbstractJFrameComponent {

    @Override
    public void initContent() {

        Container container = jFrame.getContentPane();
        GridLayout layout = new GridLayout(7, 1);
        container.setLayout(layout);

        //1.初始化登录面板
        initUserPanel(container);

        //2.初始化对象配置面板
        initObjectPanel(container);

        //3.初始化文件配置面板
        initFilePanel(container);

        //4.时空域和数据类型面板
        initDataWrapPanel(container);

        //5.字段信息和对象类型面板
        initObjectWrapPanel(container);

        //6.进度条面板
        initProgress(container);

        //7.导入和关闭按钮面板
        initButton(container);

        jFrame.setVisible(true);
    }

    /**
     * 将导入和关闭按钮加入到容器中
     *
     * @param container
     */
    private void initButton(Container container) {
        //将按钮就行布局管理

        Container button_container = new Container();
        //GridLayout layout = new GridLayout(1,4,10,10);
        GridBagLayout layout = new GridBagLayout();
        button_container.setLayout(layout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.EAST;

        JButton importButton = JButtonComponent.createButton("导入");
        importButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(jFrame, "导入成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        });
        JButton closeButton = JButtonComponent.createButton("关闭");
        closeButton.addActionListener(e -> {
            jFrame.dispose();
            System.exit(0);
        });


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1;
        button_container.add(importButton, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1;
        button_container.add(closeButton, gridBagConstraints);

        container.add(button_container);
    }

    /**
     * 初始化进度条信息
     *
     * @param container
     */
    private void initProgress(Container container) {
        Container container1 = new Container();
        GridLayout gridLayout = new GridLayout(2, 1);
        container1.setLayout(gridLayout);

        JLabel userLabel = JLabelComponent.createLabel("");

        Container progressContainer = new Container();
        GridBagLayout layout = new GridBagLayout();
        progressContainer.setLayout(layout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBorder(ComponentStyle.DEFAULT_BORDER);
        progressBar.setFont(ComponentStyle.DEFAULT_FONT);
        progressBar.setStringPainted(true);
        progressBar.setBackground(ComponentStyle.DEFAULT_COLOR);

        int minValue = 0;
        int maxValue = 100;
        AtomicInteger currentValue = new AtomicInteger();

        progressBar.setMinimum(minValue);
        progressBar.setMaximum(maxValue);

        progressBar.setValue(currentValue.get());

        // 绘制百分比文本（进度条中间显示的百分数）
        progressBar.setStringPainted(true);

        // 添加进度改变通知
        progressBar.addChangeListener(e -> {
            System.out.println("当前进度值: " + progressBar.getValue() + "; " +
                    "进度百分比: " + progressBar.getPercentComplete());
        });


        new Timer(500, e -> {
            currentValue.getAndIncrement();
            if (currentValue.get() > maxValue) {
                currentValue.set(minValue);
            }
            progressBar.setValue(currentValue.get());
        }).start();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        progressContainer.add(progressBar, gridBagConstraints);

        container1.add(userLabel);
        container1.add(progressContainer);

        container.add(container1);
    }

    private void initDataWrapPanel(Container container) {

        Container dataContainer = new Container();
        GridLayout layout = new GridLayout(1, 2);
        dataContainer.setLayout(layout);

        initDomainContentPanel(dataContainer);
        initDataTypeContentPanel(dataContainer);

        container.add(dataContainer);
    }

    private void initObjectWrapPanel(Container container) {

        Container objectContainer = new Container();
        GridLayout layout = new GridLayout(1, 2);
        objectContainer.setLayout(layout);

        initObjectContentPanel(objectContainer);
        initObjectTypeContentPanel(objectContainer);

        container.add(objectContainer);
    }

    private void initObjectTypeContentPanel(Container container) {
        Container objectTypeContainer = new Container();
        GridLayout domainLayout = new GridLayout(3, 1);
        objectTypeContainer.setLayout(domainLayout);

        JLabel jLabel = JLabelComponent.createLabel("对象类型");
        objectTypeContainer.add(jLabel);

        JPanel objectTypePanel = JPanelComponent.createPanel(ComponentTypeEnum.OBJECT_TYPE);
        /** 初始化搜索组件 */
        JSearchComponent.initSearch(ComponentTypeEnum.OBJECT);
        /** 初始化分页组件 */
        JPageComponent.initPage();

        objectTypeContainer.add(jLabel);

        container.add(objectTypeContainer);
    }

    private void initObjectContentPanel(Container container) {

        Container objectContainer = new Container();
        GridLayout domainLayout = new GridLayout(3, 1);
        objectContainer.setLayout(domainLayout);

        JLabel objectLabel = JLabelComponent.createLabel("对象");
        objectContainer.add(objectLabel);

        JPanel objectPanel = JPanelComponent.createPanel(ComponentTypeEnum.OBJECT);
        /** 下拉框封装 */
        JComboBox objectFieldComboBox = JSelectComponent.createSelect(SelectTypeEnum.FIELD);
        wrapContentWidth(DEFAULT_TEXTAREA);

        JButton searchButton = JButtonComponent.createButton("查询字段");
        searchButton.setLocation(CONTENT_WIDTH + wrapContentWidth(ComponentStyle.BORDER_DISTANCE), CONTENT_HEIGHT);
        searchButton.setSize(buttonWidth(3), wrapContentHeight(ComponentStyle.BUTTON_HEIGHT));
        searchButton.addActionListener(event -> {
            JOptionPane.showMessageDialog(jFrame, "查询成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        });
        objectContainer.add(objectLabel);

        /** 表格面板 */
        JPanel fieldPanel = JPanelComponent.createPanel(ComponentTypeEnum.DATA_TYPE);
        fieldPanel.setBackground(Color.white);
        fieldPanel.setBounds(ComponentStyle.BORDER_DISTANCE, CONTENT_HEIGHT + wrapContentHeight(ComponentStyle.CONTENT_BORDER_DISTANCE), DEFAULT_TEXTAREA + buttonWidth(4), columnHeight(4));

        JTable jTable = JTableComponent.initTable();
        //双击查看详细信息
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //获得行位置
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()) + 1;
                    //获得列位置
                    int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
                    System.out.println("row: " + row + "col: " + col);
                }
            }
        });
        JPanel tablePanel = JTableComponent.initTablePanel(fieldPanel, jTable);

        objectContainer.add(tablePanel);

        container.add(objectContainer);
    }

    private void initDomainContentPanel(Container container) {

        Container domainContainer = new Container();
        GridLayout domainLayout = new GridLayout(3, 1);
        domainContainer.setLayout(domainLayout);

        JLabel domainLabel = JLabelComponent.createLabel("时空域");
        domainContainer.add(domainLabel);

        Container configContainer = new Container();
        GridBagLayout layout = new GridBagLayout();
        configContainer.setLayout(layout);

        /** 初始化搜索组件 */
//        JSearchComponent.initSearch(ComponentTypeEnum.DOMAIN);

        JTextField searchField = JInputComponent.createInput();
        JButton searchButton = JButtonComponent.createButton("查 询");
        searchButton.addActionListener(event -> {
            System.out.println("查询");
        });

        configContainer.add(searchField);
        configContainer.add(searchButton);


        /** 初始化分页组件 */
        JPageComponent.initPage();

        //将搜索组件加入
        domainContainer.add(configContainer);

        container.add(domainContainer);
    }

    private void initDataTypeContentPanel(Container container) {

        Container dataTypeContainer = new Container();
        GridLayout domainLayout = new GridLayout(2, 1);
        dataTypeContainer.setLayout(domainLayout);

        JLabel dataTypeLabel = JLabelComponent.createLabel("数据类型");
        dataTypeContainer.add(dataTypeLabel);


        JPanel dataTypePanel = JPanelComponent.createPanel(ComponentTypeEnum.DATA_TYPE);
        /** 初始化搜索组件 */
        JSearchComponent.initSearch(ComponentTypeEnum.DATA_TYPE);
        /** 初始化分页组件 */
        JPageComponent.initPage();
        dataTypeContainer.add(dataTypePanel);

        container.add(dataTypeContainer);
    }

    private void initObjectPanel(Container container) {
        Container userInfoContainer = new Container();
        GridLayout userInfolayout = new GridLayout(2, 1);
        userInfoContainer.setLayout(userInfolayout);

        JLabel objectLabel = new JLabel("对象输出");
        objectLabel.setFont(ComponentStyle.DEFAULT_FONT);
        userInfoContainer.add(objectLabel);

        //面板容器类
        Container configContainer = new Container();
        GridBagLayout layout = new GridBagLayout();
        configContainer.setLayout(layout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;


        //是否导出至线上
        JCheckBox onLine = new JCheckBox("是否直接导入到线上");
        onLine.setFont(ComponentStyle.DEFAULT_FONT);
//        onLine.isFocusable();
        onLine.addActionListener(event -> {
            System.out.println("onLine>>>>>>>" + onLine.isSelected());
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 2;
        configContainer.add(onLine, gridBagConstraints);


        //是否采用唯一OType
        JCheckBox oType = new JCheckBox("是否唯一OType");
        oType.setFont(ComponentStyle.DEFAULT_FONT);
        oType.addActionListener(event -> {
            System.out.println("oType>>>>>>>" + oType.isSelected());
        });
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 2;
        configContainer.add(oType, gridBagConstraints);

        //选择输出目录文本
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 2;
        JLabel outPathLabel = JLabelComponent.createLabel("输出目录：");
        configContainer.add(outPathLabel, gridBagConstraints);

        //路径输出文本
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1;
        JTextField outPathField = JInputComponent.createInput();
        configContainer.add(outPathField, gridBagConstraints);

        //路径选择按钮
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 1;
        JButton selectButton = JButtonComponent.createSelectButton("选择", outPathField);
        configContainer.add(selectButton, gridBagConstraints);

        userInfoContainer.add(configContainer);

        container.add(userInfoContainer);
    }

    private void initUserPanel(Container container) {

        Container userInfoContainer = new Container();
        GridLayout layout = new GridLayout(3, 1);
        userInfoContainer.setLayout(layout);

        //第一行
        JLabel userLabel = JLabelComponent.createLabel("用户信息");
        JLabel userLabel1 = JLabelComponent.createLabel("");

        //第二行
        JLabel userNameLabel = JLabelComponent.createLabel("账户：");
        JTextField userNameField = JInputComponent.createUserInput();

        JLabel passwordLabel = JLabelComponent.createLabel("密码：");
        JPasswordField passwordField = JInputComponent.createPasswordInput();

        Container userPanelContainer = new Container();
        GridBagLayout inputLayout = new GridBagLayout();
        userPanelContainer.setLayout(inputLayout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        //用户名位置设置
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 1;
        userPanelContainer.add(userNameLabel, gridBagConstraints);

        //设置账户输入框格式
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.45;
        gridBagConstraints.weighty = 1;
        userPanelContainer.add(userNameField, gridBagConstraints);

        //设置密码文本格式
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 1;
        userPanelContainer.add(passwordLabel, gridBagConstraints);

        //设置密码输入框文本格式
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.45;
        gridBagConstraints.weighty = 1;
        userPanelContainer.add(passwordField, gridBagConstraints);

        userInfoContainer.add(userLabel);
//        userInfoContainer.add(userLabel1);
        userInfoContainer.add(userPanelContainer);

        container.add(userInfoContainer);
        resetContent();
    }

    private void initFilePanel(Container container) {

        Container userInfoContainer = new Container();
        GridLayout userInfolayout = new GridLayout(2, 1);
        userInfoContainer.setLayout(userInfolayout);
        //1.文件标题
        JLabel fileLabel = JLabelComponent.createLabel("文件");
        userInfoContainer.add(fileLabel);

        Container fileInfoContainer = new Container();
        GridBagLayout layout = new GridBagLayout();
        fileInfoContainer.setLayout(layout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        //目标参照系
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.025;
        gridBagConstraints.weighty = 1;
        JLabel targetSrsLabel = JLabelComponent.createLabel("目标参照：");
        fileInfoContainer.add(targetSrsLabel, gridBagConstraints);

        //参照系
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.025;
        gridBagConstraints.weighty = 1;
        JComboBox coordinateComboBox = JSelectComponent.createSelect(SelectTypeEnum.SYSTEM);
        fileInfoContainer.add(coordinateComboBox, gridBagConstraints);

        //文件编码
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.025;
        gridBagConstraints.weighty = 1;
        JLabel fileCodeLabel = new JLabel("文件编码:");
        fileCodeLabel.setFont(ComponentStyle.DEFAULT_FONT);
        fileInfoContainer.add(fileCodeLabel, gridBagConstraints);

        //文件代码
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.025;
        gridBagConstraints.weighty = 1;
        JComboBox fileCodeComboBox = JSelectComponent.createSelect(SelectTypeEnum.ENCODING);
        fileInfoContainer.add(fileCodeComboBox, gridBagConstraints);

        //矢量文件标签
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 1;
        JLabel vectorPathLabel = JLabelComponent.createLabel("文件：");
        fileInfoContainer.add(vectorPathLabel, gridBagConstraints);

        //矢量文件路径
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1;
        JTextField vectorPathField = JInputComponent.createInput();
        fileInfoContainer.add(vectorPathField, gridBagConstraints);

        //文件选择按钮
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 1;
        JButton selectButton = JButtonComponent.createSelectButton("选择", vectorPathField);
        fileInfoContainer.add(selectButton, gridBagConstraints);

        userInfoContainer.add(fileInfoContainer);

        container.add(userInfoContainer);

    }
}
