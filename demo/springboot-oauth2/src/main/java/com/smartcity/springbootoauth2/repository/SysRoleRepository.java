package com.smartcity.springbootoauth2.repository;

import com.smartcity.springbootoauth2.entity.SysRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lipoGiser
 * @date 2020/1/14 8:52
 * @Version 0.1
 */
@Repository
public interface SysRoleRepository extends CrudRepository<SysRole,Integer> {

}
