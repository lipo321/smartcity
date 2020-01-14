package com.smartcity.springbootoauth2.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author lipoGiser
 * @date 2020/1/14 8:26
 * @Version 0.1
 */
@Entity
@Table(name = "sys_user")
public class SysUser {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name = "password")
    private String password;

    @Transient
    private List<SysRole> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
