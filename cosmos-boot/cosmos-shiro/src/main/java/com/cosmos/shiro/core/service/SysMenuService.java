package com.cosmos.shiro.core.service;

import com.cosmos.shiro.common.service.BaseService;
import com.cosmos.shiro.core.dto.SysMenuDTO;
import com.cosmos.shiro.core.entity.SysMenu;
import com.cosmos.shiro.core.vo.SysMenuVO;

import java.util.List;

/**
 * @Description 权限业务接口
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
public interface SysMenuService extends BaseService<SysMenu, SysMenuVO, SysMenuDTO> {


    /**
     * 根据角色查询用户权限
     *
     * @Author cosmos
     * @CreateTime 2019/6/19 10:14
     * @Param roleId 角色ID
     * @Return List<SysMenuEntity> 权限集合
     */
    List<SysMenu> selectSysMenuByRoleId(Long roleId);

    void saveMenu(SysMenuDTO sysMenuDTO);

}

