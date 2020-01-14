package com.smartcity.springbootoauth2.repository;

import com.smartcity.springbootoauth2.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lipoGiser
 * @date 2020/1/14 8:51
 * @Version 0.1
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission,Integer> {
}
