//package com.shape.tool.view.component;

package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;
import com.example.demo.dict.ComponentTypeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JPageComponent extends AbstractDefaultComponent {

    public static void initPage() {

        JPanel tableWrapPanel = JPanelComponent.createPanel(ComponentTypeEnum.OBJECT_TYPE);
        tableWrapPanel.setLocation(ComponentStyle.BORDER_DISTANCE,
                CONTENT_HEIGHT + wrapContentHeight(ComponentStyle.CONTENT_BORDER_DISTANCE));
        tableWrapPanel.setSize(DEFAULT_TEXTAREA, columnHeight(4));

        JTable jTable = JTableComponent.initTable();
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {     //双击查看详细信息
                if (e.getClickCount() == 2) {
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()) + 1; //获得行位置
                    int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint()); //获得列位置
                    System.out.println("row: " + row + "col: " + col);
                }
            }
        });
        JPanel tablePanel = JTableComponent.initTablePanel(tableWrapPanel, jTable);

        JButton nextButton = JButtonComponent.createButton("下一页");
        nextButton.setLocation(CONTENT_WIDTH, CONTENT_HEIGHT);
        wrapContentHeight(ComponentStyle.BUTTON_HEIGHT);

        nextButton.addActionListener(event -> {
            if (TOTAL_PAGE <= 0) {
                return;
            } else {
                if (CURRENT_PAGE < TOTAL_PAGE) {
                    CURRENT_PAGE++;
                } else {
                    CURRENT_PAGE = 1;
                }
            }
        });

        JButton prveButton = JButtonComponent.createButton("上一页");
        prveButton.setLocation(CONTENT_WIDTH, CONTENT_HEIGHT + wrapContentHeight(ComponentStyle.CONTENT_BORDER_DISTANCE));
        wrapContentHeight(ComponentStyle.BUTTON_HEIGHT);

        prveButton.addActionListener(event -> {
            if (TOTAL_PAGE <= 0) {
                return;
            } else {
                if (CURRENT_PAGE > 1) {
                    CURRENT_PAGE--;
                } else {
                    CURRENT_PAGE = TOTAL_PAGE;
                }
            }
        });


        JTextField pageField = JInputComponent.createInput();
        pageField.setLocation(CONTENT_WIDTH, CONTENT_HEIGHT + wrapContentHeight(85));
        pageField.setSize(buttonWidth(0), ComponentStyle.INPUT_HEIGHT);


        JLabel tipLabel = JLabelComponent.createLabel("/");
        tipLabel.setLocation(CONTENT_WIDTH + 40, CONTENT_HEIGHT);

        JLabel totalLabel = JLabelComponent.createLabel("");
        totalLabel.setLocation(CONTENT_WIDTH + 55, CONTENT_HEIGHT);
        totalLabel.setText(String.valueOf(TOTAL_PAGE));

    }

}
