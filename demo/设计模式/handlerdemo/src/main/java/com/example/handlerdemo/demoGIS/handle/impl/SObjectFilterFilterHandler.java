package com.example.handlerdemo.demoGIS.handle.impl;

import com.example.handlerdemo.demoGIS.filter.SpatialFilter;
import com.example.handlerdemo.demoGIS.handle.AbstractFilterHandler;
import com.example.handlerdemo.demoGIS.model.SObject;
import com.example.handlerdemo.demoGIS.model.SObjectEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SObjectFilterFilterHandler<TEntity> extends AbstractFilterHandler<List<SObject>, SpatialFilter<SObject>> {

    private TEntity entity;

    public void setEntity(TEntity entity) {
        this.entity = entity;
    }

    @Override
    public List<SObject> filter(SpatialFilter<SObject> filter) throws Exception {
//        Page<SObjectEntity> sObjectEntities = (Page<SObjectEntity>)entity;
//        Map<Long, Version> versionsByVIds = getLongVersionMap(sObjectEntities);
        List<SObject> sObjects = new ArrayList<>();
//        for (SObjectEntity sObjectEntity : sObjectEntities) {
//            SObject sObject = SObjectEntity.createSObject(sObjectEntity, versionsByVIds);
//            sObjects.add(sObject);
//        }
        System.out.println("根据查询条件，查询对象所有实体信息，并转换成业务模型");
        return sObjects;
    }
}
