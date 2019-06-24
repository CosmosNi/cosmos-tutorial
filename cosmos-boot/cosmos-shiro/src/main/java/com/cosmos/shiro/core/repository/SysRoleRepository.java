package com.cosmos.shiro.core.repository;

import com.cosmos.shiro.common.repository.BaseRepository;
import com.cosmos.shiro.core.entity.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by $!{author} on $!{now}.
 */
public interface SysRoleRepository extends BaseRepository<SysRole> {

    @Query(value = "        SELECT sr.* FROM sys_role sr " +
            "LEFT JOIN sys_user_role se ON sr.id=se.role_id " +
            "WHERE se.user_id = :userId", nativeQuery = true)
    List<SysRole> selectSysRoleByUserId(@Param(value = "userId") Long userId);
}