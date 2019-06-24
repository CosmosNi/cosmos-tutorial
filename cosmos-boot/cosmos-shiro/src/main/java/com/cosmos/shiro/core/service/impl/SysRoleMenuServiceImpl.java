package com.cosmos.shiro.core.service.impl;

import com.cosmos.shiro.common.service.impl.BaseServiceImpl;
import com.cosmos.shiro.core.dto.SysRoleMenuDTO;
import com.cosmos.shiro.core.entity.SysRoleMenu;
import com.cosmos.shiro.core.repository.SysRoleMenuRepository;
import com.cosmos.shiro.core.service.SysRoleMenuService;
import com.cosmos.shiro.core.vo.SysRoleMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 角色与权限业务实现
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu, SysRoleMenuVO, SysRoleMenuDTO> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Override
    public void bindMenuToRole(Long roleId, List<Long> menuIds) {
        menuIds.stream().forEach(menuId -> {
            SysRoleMenu sysRoleMenu = SysRoleMenu.builder()
                    .menuId(menuId)
                    .roleId(roleId).build();
            this.save(sysRoleMenu);
        });
    }
}