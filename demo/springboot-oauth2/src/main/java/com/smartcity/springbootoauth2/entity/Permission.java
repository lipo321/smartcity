package com.smartcity.springbootoauth2.entity;

import javax.persistence.*;

/**
 * @author lipoGiser
 * @date 2020/1/14 8:16
 * @Version 0.1
 */
@Entity
@Table(name="sys_permission")
public class Permission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     *权限名称
     */
    @Column(name="name")
    private String name;


    /**
     * 权限描述
     */
    @Column(name="description")
    private String description;

    /**
     * 权限链接
     */
    @Column(name="url")
    private String url;

    /**
     * 父节点id
     */
    @Column(name="pid")
    private int pid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
