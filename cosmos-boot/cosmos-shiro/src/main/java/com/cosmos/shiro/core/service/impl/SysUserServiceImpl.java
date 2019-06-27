package com.cosmos.shiro.core.service.impl;

import com.cosmos.shiro.common.service.impl.BaseServiceImpl;
import com.cosmos.shiro.common.util.SHA256Util;
import com.cosmos.shiro.common.util.ShiroUtils;
import com.cosmos.shiro.core.dto.SysUserDTO;
import com.cosmos.shiro.core.entity.SysUser;
import com.cosmos.shiro.core.repository.SysUserRepository;
import com.cosmos.shiro.core.service.SysUserRoleService;
import com.cosmos.shiro.core.service.SysUserService;
import com.cosmos.shiro.core.vo.SysUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 系统用户业务实现
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserVO, SysUserDTO> implements SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser selectUserByName(String username) {

        return sysUserRepository.findByUsername(username).get();
    }

    @Override
    public void saveDTO(SysUserDTO sysUserDTO) {
        SysUser sysUser = new SysUser();
        String salt = ShiroUtils.randomSalt();
        BeanUtils.copyProperties(sysUserDTO, sysUser);
        sysUser.setSalt(salt);
        sysUser.setPassword(SHA256Util.sha256(sysUserDTO.getPassword(), salt));
        this.save(sysUser);
        sysUserRoleService.bindRoleToUser(sysUser.getId(), sysUserDTO.getRoleIds());

    }


}