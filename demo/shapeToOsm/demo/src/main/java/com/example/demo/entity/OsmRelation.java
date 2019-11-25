package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 关系类型几何
 *
 * @author lipo@126.com
 * @date 2019年7月3日
 */
@Setter
@Getter
public class OsmRelation extends OsmGraph {

    private static final long serialVersionUID = 485103440640977116L;

    private Long id;

    private Integer flag;

    private Map<String, String> tags;


}
