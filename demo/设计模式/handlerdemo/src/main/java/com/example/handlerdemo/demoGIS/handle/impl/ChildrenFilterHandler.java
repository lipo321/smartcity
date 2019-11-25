package com.example.handlerdemo.demoGIS.handle.impl;

import com.example.handlerdemo.demoGIS.filter.SpatialFilter;
import com.example.handlerdemo.demoGIS.handle.AbstractFilterHandler;
import com.example.handlerdemo.demoGIS.model.SObject;
import com.example.handlerdemo.demoGIS.service.SObjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ChildrenFilterHandler extends AbstractFilterHandler<List<SObject>, SpatialFilter<SObject>> {
    @Autowired
    private SObjectService sObjectService;

    @Override
    public List<SObject> filter(SpatialFilter<SObject> filter) throws Exception {

        List<SObject> sObjects = this.data;

        System.out.println("根据是否加载子，进行相应的处理并返回对象集合");
        return sObjects;
    }
}
