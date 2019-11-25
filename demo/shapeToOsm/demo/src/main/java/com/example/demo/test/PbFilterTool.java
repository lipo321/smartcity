package com.example.demo.test;

import onegis.common.utils.GeneralUtils;
import onegis.filter.GFilter;
import onegis.protobuf.model.PbFormatData;
import onegis.psde.dictionary.OrderByEnum;
import onegis.psde.dictionary.SpatialRelationType;
import onegis.psde.filter.NameFilter;
import onegis.psde.filter.OTypeFilter;
import onegis.psde.filter.SpatialFilter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class PbFilterTool {


    /**
     * PbSpatialFilter 转 SpatialFilter
     *
     * @param filter PbSpatialFilter
     * @return SpatialFilter
     */
    public static SpatialFilter pbSpatialFilterTods(PbFormatData.PbSpatialFilter filter) {

        SpatialFilter spatialFilter = new SpatialFilter<>();
        // AFilter
        spatialFilter.setPageNum(filter.getPageNum());
        spatialFilter.setPageSize(filter.getPageSize());
        spatialFilter.setOrderType(OrderByEnum.getEnum(filter.getOrderType()));
        spatialFilter.setDescOrAsc(filter.getDescOrAsc());
        spatialFilter.setLoadDynamicData(filter.getLoadDynamicData());
        spatialFilter.setGeoClip(filter.getGeoClip());
        setUserFilterByPb(filter, spatialFilter);
        setIdOrNameFilterByPb(filter, spatialFilter);
        setTFilterByPb(filter, spatialFilter);
        setOTypeFilterBypb(filter, spatialFilter);
        setSpatiFilterByPb(filter, spatialFilter);
        return spatialFilter;
    }

    public static void setSpatiFilterByPb(PbFormatData.PbSpatialFilter filter, SpatialFilter spatialFilter) {
        // 空间过滤器
        spatialFilter.setGeoEdit(filter.getGeoEdit());
        spatialFilter.setLoadForm(filter.getLoadForm());
        spatialFilter.setSrType(SpatialRelationType.getEnum(filter.getSrType()));
        if (GeneralUtils.isNotEmpty(filter.getGeoWkt())) {
            spatialFilter.setGeoWkt(filter.getGeoWkt());
        }

        List<Integer> formTypesList = filter.getFormTypesList();
        if (GeneralUtils.isNotEmpty(formTypesList)) {
            spatialFilter.setFormTypes(new HashSet<>(formTypesList));
        }

        spatialFilter.setGrain(filter.getGrain());
        spatialFilter.setLoadChildren(filter.getLoadChildren());

        List<Long> parentsList = filter.getParentsList();
        if (GeneralUtils.isNotEmpty(parentsList)) {
            spatialFilter.setParents(new HashSet<>(parentsList));
        }
        spatialFilter.setLoadNetwork(filter.getLoadNetwork());
        spatialFilter.setLoadCompose(filter.getLoadCompose());
    }

    /**
     * 动态属性过滤器转换
     *
     * @param filter
     * @return
     */
    public static GFilter pbDynamicDataFilterTods(PbFormatData.PbDynamicDataFilter filter) {
        GFilter gFilter = new GFilter();
        List<Long> oidsList = filter.getOidsList();
        if (GeneralUtils.isNotEmpty(oidsList)) {
            gFilter.setOids(new HashSet<>(oidsList));
        }
        String tableName = filter.getTableName();
        if (!"".equals(tableName)) {
            gFilter.setTableName(tableName);
        }
        if (filter.getStartTime() != 0) {
            gFilter.setStartTime(new Date(filter.getStartTime()));
        }
        if (filter.getEndTime() != 0) {
            gFilter.setEndTime(new Date(filter.getEndTime()));
        }
        String bbox = filter.getBbox();
        if (!"".equals(bbox)) ;
        {
            gFilter.setBbox(bbox);
        }
        return gFilter;
    }

    /**
     * 设置对象类过滤器
     *
     * @param filter        PbSpatialFilter
     * @param spatialFilter SpatialFilter
     */
    public static void setOTypeFilterBypb(PbFormatData.PbSpatialFilter filter, SpatialFilter spatialFilter) {

        spatialFilter.setLoadObjType(filter.getLoadObjType());
        spatialFilter.setLoadAttr(filter.getLoadAttr());
        List<String> fieldNamesList = filter.getFieldNamesList();
        if (GeneralUtils.isNotEmpty(fieldNamesList)) {
            spatialFilter.setFieldNames(new HashSet<>(fieldNamesList));
        }
        if (GeneralUtils.isNotEmpty(filter.getWhereStatment())) {
            spatialFilter.setWhereStatment(filter.getWhereStatment());
        }

        List<Long> otIdsList = filter.getOtIdsList();
        if (GeneralUtils.isNotEmpty(otIdsList)) {
            spatialFilter.setOtIds(new HashSet<>(otIdsList));
        }

        List<String> otNames = filter.getOtNamesList();
        if (GeneralUtils.isNotEmpty(otNames)) {
            spatialFilter.setOtNames(new HashSet<>(otNames));
        }

        List<Long> fromIdsList = filter.getFromIdsList();
        if (GeneralUtils.isNotEmpty(fromIdsList)) {
            spatialFilter.setFromIds(new HashSet<>(fromIdsList));
        }
    }

    /**
     * 设置时间过滤器
     *
     * @param filter        PbSpatialFilter
     * @param spatialFilter SpatialFilter
     */
    public static void setTFilterByPb(PbFormatData.PbSpatialFilter filter, SpatialFilter spatialFilter) {

        spatialFilter.setLoadVersion(filter.getLoadVersion());
        spatialFilter.setLoadAction(filter.getLoadAction());
        List<Long> versions = filter.getVersionsList();
        if (GeneralUtils.isNotEmpty(versions)) {
            spatialFilter.setVersions(new HashSet<>(versions));
        }

        if (filter.getBeginTime() != 0) {
            spatialFilter.setBeginTime(new Date(filter.getBeginTime()));
        }

        if (filter.getEndTime() != 0) {
            spatialFilter.setEndTime(new Date(filter.getEndTime()));
        }

        List<Long> sDomains = filter.getSdomainsList();
        if (GeneralUtils.isNotEmpty(sDomains)) {
            spatialFilter.setSdomains(new HashSet<>(sDomains));
        }
    }

    /**
     * 设置ID过滤器和Name过滤器
     *
     * @param filter        PbSpatialFilter
     * @param spatialFilter SpatialFilter
     */
    public static void setIdOrNameFilterByPb(PbFormatData.PbSpatialFilter filter, SpatialFilter spatialFilter) {

        // IDFilter
        List<Long> ids = filter.getIdsList();
        if (GeneralUtils.isNotEmpty(ids)) {
            spatialFilter.setIds(new HashSet<>(ids));
        }

        // NameFilter
        spatialFilter.setLoadDes(filter.getLoadDes());
        List<String> names = filter.getNamesList();
        if (GeneralUtils.isNotEmpty(names)) {
            spatialFilter.setNames(new HashSet<>(names));
        }
    }

    /**
     * 设置用户过滤器
     *
     * @param filter        PbSpatialFilter
     * @param spatialFilter SpatialFilter
     */
    public static void setUserFilterByPb(PbFormatData.PbSpatialFilter filter, SpatialFilter spatialFilter) {

        List<String> uIds = filter.getUidsList();
        if (GeneralUtils.isNotEmpty(uIds)) {
            HashSet<String> uidList = new HashSet<>();
            for (String uid : uIds) {
                uidList.add("\'" + uid + "\'");
            }
            spatialFilter.setUids(uidList);
        }
    }

    /**
     * pbNameFilter 转 dsNameFilter 名称过滤器
     *
     * @param filter PbNameFilter
     * @return NameFilter
     */
    public static NameFilter pbNameFilterTods(PbFormatData.PbNameFilter filter) {

        NameFilter nameFilter = new NameFilter();
        nameFilter.setPageNum(filter.getPageNum());
        nameFilter.setPageNum(filter.getPageNum());
        nameFilter.setOrderType(OrderByEnum.getEnum(filter.getOrderType()));
        nameFilter.setDescOrAsc(filter.getDescOrAsc());
        nameFilter.setLoadDes(filter.getLoadDes());

        List<Long> ids = filter.getIdsList();
        if (GeneralUtils.isNotEmpty(ids)) {
            nameFilter.setIds(new HashSet<>(ids));
        }
        List<String> names = filter.getNamesList();
        if (GeneralUtils.isNotEmpty(names)) {
            nameFilter.setNames(new HashSet<>(names));
        }

        // UserFilter
        List<String> uIds = filter.getUidsList();
        if (GeneralUtils.isNotEmpty(uIds)) {
            HashSet<String> uidList = new HashSet<>();
            for (String uid : uIds) {
                uidList.add("\'" + uid + "\'");
            }
            nameFilter.setUids(uidList);
        }
        return nameFilter;
    }

    /**
     * pbOTypeFilter 转 dsOTypeFilter 对象类过滤器
     *
     * @param filter PbOTypeFilter
     * @return OTypeFilter
     */
    public static OTypeFilter pbOTypeFilterTods(PbFormatData.PbOTypeFilter filter) {

        OTypeFilter otypeFilter = new OTypeFilter();
        // AFilter
        otypeFilter.setPageNum(filter.getPageNum());
        otypeFilter.setPageSize(filter.getPageSize());
        otypeFilter.setOrderType(OrderByEnum.getEnum(filter.getOrderType()));
        otypeFilter.setDescOrAsc(filter.getDescOrAsc());

        // UserFilter
        List<String> uIds = filter.getUidsList();
        if (GeneralUtils.isNotEmpty(uIds)) {
            otypeFilter.setUids(new HashSet<>(uIds));
        }

        // IDFilter
        List<Long> ids = filter.getIdsList();
        if (GeneralUtils.isNotEmpty(ids)) {
            otypeFilter.setIds(new HashSet<>(ids));
        }

        // NameFilter
        otypeFilter.setLoadDes(filter.getLoadDes());
        List<String> names = filter.getNamesList();
        if (GeneralUtils.isNotEmpty(names)) {
            otypeFilter.setNames(new HashSet<>(names));
        }

        // TagFilter
        otypeFilter.setLoadTag(filter.getLoadTag());
        List<String> tags = filter.getTagsList();
        if (GeneralUtils.isNotEmpty(tags)) {
            otypeFilter.setTags(new HashSet<>(tags));
        }

        // OTypeFilter
        otypeFilter.setLoadStRs(filter.getLoadStRs());
        otypeFilter.setLoadField(filter.getLoadField());
        otypeFilter.setLoadParents(filter.getLoadParents());
        otypeFilter.setLoadForm(filter.getLoadForm());
        otypeFilter.setLoadConnector(filter.getLoadConnector());
        otypeFilter.setLoadModel(filter.getLoadModel());
        return otypeFilter;
    }
}
