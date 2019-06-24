package com.cosmos.shiro.core.service;

import com.cosmos.shiro.common.service.BaseService;
import com.cosmos.shiro.core.dto.SysRoleMenuDTO;
import com.cosmos.shiro.core.entity.SysRoleMenu;
import com.cosmos.shiro.core.vo.SysRoleMenuVO;

import java.util.List;

/**
 * @Description 角色与权限业务接口
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu, SysRoleMenuVO, SysRoleMenuDTO> {
    void bindMenuToRole(Long roleId, List<Long> menuIds);
}

