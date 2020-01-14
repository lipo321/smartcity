package com.example.demo.entity;

import com.example.demo.dict.GeomType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 对象几何实体
 *
 * @author lipo@126.com
 * @date 2019年7月3日
 */
@Getter
@Setter
public class OsmEntity implements Serializable {

    private static final long     serialVersionUID = -1662444565087894515L;
    private              GeomType type;

    private Long id;

    private Long uuid;

    private Integer flag;

    private OsmGraph nodes;

}
