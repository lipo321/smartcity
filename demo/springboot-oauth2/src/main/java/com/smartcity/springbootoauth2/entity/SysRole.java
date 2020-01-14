package com.smartcity.springbootoauth2.entity;

import javax.persistence.*;

/**
 * @author lipoGiser
 * @date 2020/1/14 8:24
 * @Version 0.1
 */
@Entity
@Table(name="sys_role")
public class SysRole {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name="name")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
