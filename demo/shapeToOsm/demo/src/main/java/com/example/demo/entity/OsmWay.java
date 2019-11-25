package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Way类型几何
 *
 * @author lipo@126.com
 * @date 2019年7月3日
 */
@Getter
@Setter
public class OsmWay extends OsmGraph {

    private static final long serialVersionUID = 2414037230914364731L;

    private Long id;

    private Long uuid;

    private Integer flag;

    private List<OsmNode> nodes = new ArrayList<>();
}
