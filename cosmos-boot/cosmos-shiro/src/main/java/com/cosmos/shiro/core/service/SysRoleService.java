package com.cosmos.shiro.core.service;

import com.cosmos.shiro.common.service.BaseService;
import com.cosmos.shiro.core.dto.SysRoleDTO;
import com.cosmos.shiro.core.entity.SysRole;
import com.cosmos.shiro.core.vo.SysRoleVO;

import java.util.List;

/**
 * @Description 角色业务接口
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
public interface SysRoleService extends BaseService<SysRole, SysRoleVO, SysRoleDTO> {

    /**
     * 通过用户ID查询角色集合
     *
     * @Author cosmos
     * @CreateTime 2019/6/18 18:01
     * @Param userId 用户ID
     * @Return List<sysRole> 角色名集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);

    void saveRole(SysRoleDTO sysRoleDTO);
}

