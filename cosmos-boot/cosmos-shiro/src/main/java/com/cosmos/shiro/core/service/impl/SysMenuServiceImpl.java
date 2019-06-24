package com.cosmos.shiro.core.service.impl;


import com.cosmos.shiro.common.service.impl.BaseServiceImpl;
import com.cosmos.shiro.core.dto.SysMenuDTO;
import com.cosmos.shiro.core.entity.SysMenu;
import com.cosmos.shiro.core.repository.SysMenuRepository;
import com.cosmos.shiro.core.service.SysMenuService;
import com.cosmos.shiro.core.vo.SysMenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 权限业务实现
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuVO, SysMenuDTO> implements SysMenuService {
    @Autowired
    private SysMenuRepository sysMenuRepository;

    /**
     * 根据角色查找用户权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> selectSysMenuByRoleId(Long roleId) {
        return sysMenuRepository.selectSysMenuByRoleId(roleId);
    }

    @Override
    public void saveMenu(SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuDTO, sysMenu);
        this.save(sysMenu);
    }


}