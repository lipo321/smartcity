package com.example.handlerdemo.demoGIS.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpatialFilter<KT> extends DataFilter<KT> {
    private Integer pageSize;
}
