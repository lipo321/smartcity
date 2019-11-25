package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 点类型
 *
 * @author lipo@126.com
 * @date 2019年7月3日
 */
@Getter
@Setter
public class OsmNode extends OsmGraph {

    private static final long serialVersionUID = 2741667923818179815L;
    private String type;

    private Long id;

    private Long uuid;

    private Boolean flag;

    private Double x;

    private Double y;

    private Double z;
}
