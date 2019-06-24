package com.cosmos.shiro.core.service.impl;

import com.cosmos.shiro.common.service.impl.BaseServiceImpl;
import com.cosmos.shiro.core.dto.SysUserRoleDTO;
import com.cosmos.shiro.core.entity.SysUserRole;
import com.cosmos.shiro.core.service.SysUserRoleService;
import com.cosmos.shiro.core.vo.SysUserRoleVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户与角色业务实现
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, SysUserRoleVO, SysUserRoleDTO> implements SysUserRoleService {

    @Override
    public void bindRoleToUser(Long userId, List<Long> roleIds) {
        roleIds.stream().forEach(roleId -> {
            SysUserRole sysUserRole = SysUserRole.builder()
                    .roleId(roleId)
                    .userId(userId).build();
            this.save(sysUserRole);
        });
    }
}