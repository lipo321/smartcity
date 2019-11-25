package com.example.demo.entity;

import com.example.demo.dict.GeomType;
import com.example.demo.dict.OsmRole;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * OSM成员
 *
 * @author lipo@126.com
 * @date 2019年7月3日
 */
@Getter
@Setter
public class OsmMember implements Serializable {


    private static final long serialVersionUID = 5483156175432152907L;

    private GeomType geomType;

    private OsmRole role;

    private OsmGraph graph;

}
