package com.smartcity.springbootoauth2.repository;

import com.smartcity.springbootoauth2.entity.SysUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author lipoGiser
 * @date 2020/1/14 8:53
 * @Version 0.1
 */
public interface SysUserRepository extends CrudRepository<SysUser,Integer> {

    /**
     * @param name
     * @return
     */
    @Query("select a from SysUser a where a.name=:name")
    public SysUser getUserByName(@Param("name") String name);
}
