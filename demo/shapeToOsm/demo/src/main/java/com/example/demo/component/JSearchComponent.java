//package com.shape.tool.view.component;

package com.example.demo.component;


import com.example.demo.component.style.ComponentStyle;
import com.example.demo.dict.ComponentTypeEnum;

import javax.swing.*;
import java.awt.*;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JSearchComponent extends AbstractDefaultComponent {

    public static void initSearch(ComponentTypeEnum componentTypeEnum) {

        //根据不同类型进行数据查询，但是逻辑上不应该是这样的
        checkType(componentTypeEnum);
        JTextField searchField = JInputComponent.createInput();


        JButton searchButton = JButtonComponent.createButton("查 询");

        searchButton.addActionListener(event -> {
            System.out.println("查询");
        });

    }

    public static void checkType(ComponentTypeEnum componentTypeEnum) {
        //TODO 针对不同类型的查询更新不同的数据
        switch (componentTypeEnum) {
            case DATA_TYPE:
                break;
            case DOMAIN:
                break;
            case OBJECT:
                break;
            default:
                break;
        }
    }

}
