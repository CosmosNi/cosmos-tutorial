package com.cosmos.shiro.core.repository;

import com.cosmos.shiro.common.repository.BaseRepository;
import com.cosmos.shiro.core.entity.SysMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by $!{author} on $!{now}.
 */
public interface SysMenuRepository extends BaseRepository<SysMenu> {
    @Query(value = "SELECT sm.* FROM sys_menu sm\n" +
            "      LEFT JOIN sys_role_menu se ON sm.id = se.menu_id\n" +
            "      WHERE se.role_id = :roleId", nativeQuery = true)
    List<SysMenu> selectSysMenuByRoleId(@Param(value = "roleId") Long roleId);
}