package com.cosmos.shiro.core.service;

import com.cosmos.shiro.common.service.BaseService;
import com.cosmos.shiro.core.dto.SysUserRoleDTO;
import com.cosmos.shiro.core.entity.SysUserRole;
import com.cosmos.shiro.core.vo.SysUserRoleVO;

import java.util.List;

/**
 * @Description 用户与角色业务接口
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
public interface SysUserRoleService extends BaseService<SysUserRole, SysUserRoleVO, SysUserRoleDTO> {
    void bindRoleToUser(Long userId, List<Long> roleIds);
}

