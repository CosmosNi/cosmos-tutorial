package com.cosmos.shiro.core.service.impl;

import com.cosmos.shiro.common.service.impl.BaseServiceImpl;
import com.cosmos.shiro.core.dto.SysRoleDTO;
import com.cosmos.shiro.core.entity.SysRole;
import com.cosmos.shiro.core.repository.SysRoleRepository;
import com.cosmos.shiro.core.service.SysRoleMenuService;
import com.cosmos.shiro.core.service.SysRoleService;
import com.cosmos.shiro.core.vo.SysRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 角色业务实现
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleVO, SysRoleDTO> implements SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 通过用户ID查询角色集合
     *
     * @Author cosmos
     * @CreateTime 2019/6/18 18:01
     * @Param userId 用户ID
     * @Return List<sysRole> 角色名集合
     */
    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        return sysRoleRepository.selectSysRoleByUserId(userId);
    }

    @Override
    public void saveRole(SysRoleDTO sysRoleDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDTO, sysRole);

        this.save(sysRole);
        sysRoleMenuService.bindMenuToRole(sysRole.getId(), sysRoleDTO.getMenuIds());

    }
}