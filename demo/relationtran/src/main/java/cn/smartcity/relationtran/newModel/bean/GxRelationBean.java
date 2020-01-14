package cn.smartcity.relationtran.newModel.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

/**
 * <p>ComplexKey</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 10:22 2019/10/29
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GxRelationBean {

    private Long hashId;

    private Long rid;

    private Long time;


    public GxRelationBean() {
    }

    public GxRelationBean(Long rid, Long time) {
        this.rid = rid;
        this.time = time;
        this.hashId = (long) Objects.hash(this.rid, this.time);
    }

    public Long getHashId() {
        return hashId;
    }

    public void setHashId(Long hashId) {
        this.hashId = hashId;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GxRelationBean that = (GxRelationBean) o;
        return Objects.equals(rid, that.rid) &&
                Objects.equals(time, that.time);
    }


    @Override
    public int hashCode() {
        return Objects.hash(rid, time);
    }
}
