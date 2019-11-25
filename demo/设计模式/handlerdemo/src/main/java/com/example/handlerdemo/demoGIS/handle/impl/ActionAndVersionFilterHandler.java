package com.example.handlerdemo.demoGIS.handle.impl;

import com.example.handlerdemo.demoGIS.filter.SpatialFilter;
import com.example.handlerdemo.demoGIS.handle.AbstractFilterHandler;
import com.example.handlerdemo.demoGIS.model.SObject;
import com.example.handlerdemo.demoGIS.service.SObjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActionAndVersionFilterHandler extends AbstractFilterHandler<List<SObject>, SpatialFilter<SObject>> {

    @Autowired
    private SObjectService sObjectService;


    @Override
    public List<SObject> filter(SpatialFilter<SObject> filter) throws Exception {

        List<SObject> sObjects = this.data;
        System.out.println("根据过滤条件，是否加载action和version，并将处理结果塞到相应的对象中");
        return sObjects;

    }
}
