package com.example.handlerdemo.demoGIS.service;

import com.example.handlerdemo.demoGIS.filter.SpatialFilter;
import com.example.handlerdemo.demoGIS.handle.AbstractFilterHandler;
import com.example.handlerdemo.demoGIS.handle.impl.*;
import com.example.handlerdemo.demoGIS.model.SObject;
import com.example.handlerdemo.demoGIS.model.SObjectEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SObjectQueryServiceImpl {

    @Autowired
    private SObjectService sObjectService;

    public PageInfo<SObject> query(SpatialFilter<SObject> spatialFilter) throws Exception {
        Integer pageSize = spatialFilter.getPageSize();

        Page<SObjectEntity> sObjectEntities = new Page<>();
        PageInfo<SObjectEntity> page = new PageInfo<>(sObjectEntities);
        // ======== 创建SObjects任务链 ========
        SObjectFilterFilterHandler<Page<SObjectEntity>> sObjectFilterHandler = new SObjectFilterFilterHandler<>();
        OTypeFilterFilterHandler oTypeFilterHandler = new OTypeFilterFilterHandler();
        FormFilterHandler formFilterHandler = new FormFilterHandler();
        ChildrenFilterHandler childrenFilterHandler = new ChildrenFilterHandler();
        ActionAndVersionFilterHandler actionAndVersionFilterHandler = new ActionAndVersionFilterHandler();
        NetWorkFilterHandler<List<SObjectEntity>> netWorkFilterHandler = new NetWorkFilterHandler<>();
        ModelFilterHandler modelFilterHandler = new ModelFilterHandler();
        ComposeFilterHandler composeFilterHandler = new ComposeFilterHandler();
        DynamicDataFilterHandler dynamicDataFilterHandler = new DynamicDataFilterHandler();

        // ======== 设置数据 And 设置任务链的下一个链 ========
        sObjectFilterHandler.setEntity(sObjectEntities);
        sObjectFilterHandler.setNextHandler(oTypeFilterHandler);
        oTypeFilterHandler.setNextHandler(formFilterHandler);
        formFilterHandler.setNextHandler(childrenFilterHandler);
        childrenFilterHandler.setNextHandler(actionAndVersionFilterHandler);
        actionAndVersionFilterHandler.setNextHandler(netWorkFilterHandler);
        netWorkFilterHandler.setNextHandler(modelFilterHandler);
        modelFilterHandler.setNextHandler(composeFilterHandler);
        composeFilterHandler.setNextHandler(dynamicDataFilterHandler);
        List<SObject> sObjects = (List<SObject>) AbstractFilterHandler.filter(sObjectFilterHandler, spatialFilter);
        return new PageInfo<>(sObjects);
    }

    public static void main(String[] args) throws Exception {
        SObjectQueryServiceImpl sObjectQueryService = new SObjectQueryServiceImpl();
        SpatialFilter<SObject> spatialFilter = new SpatialFilter();
        sObjectQueryService.query(spatialFilter);
    }
}
