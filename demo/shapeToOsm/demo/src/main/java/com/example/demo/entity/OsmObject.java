package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象实体
 *
 * @author lipo@126.com
 * @date 2019年7月3日
 */
@Setter
@Getter
public class OsmObject implements Serializable {

    private static final long serialVersionUID = 16207088070825700L;
    //对象id
    private              int  id;

    //对象类型
    private String name;

    private boolean checkStatus;

    //    //唯一标识id？
//   private Long uuid;
//
//    //版本id
//   private Long vid;
//
//    //标签
    private Map<String, String> tags = new HashMap<>();
    //
//   private String user;
//   private String bBox;
//
//   private String cTime;
//   private String uTime;
//
    private OsmEntity           entity;

    private ObejctType type;
}
