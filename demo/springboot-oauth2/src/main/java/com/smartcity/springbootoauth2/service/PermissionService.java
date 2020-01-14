package com.smartcity.springbootoauth2.service;

import com.smartcity.springbootoauth2.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lipoGiser
 * @date 2020/1/14 8:57
 * @Version 0.1
 */
@Service
public interface PermissionService {
    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
}
