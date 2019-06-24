package com.cosmos.shiro.core.service;

import com.cosmos.shiro.common.service.BaseService;
import com.cosmos.shiro.core.dto.SysUserDTO;
import com.cosmos.shiro.core.entity.SysUser;
import com.cosmos.shiro.core.vo.SysUserVO;

import java.util.List;

/**
 * @Description 系统用户业务接口
 * @Author cosmos
 * @CreateTime 2019/6/14 15:57
 */
public interface SysUserService extends BaseService<SysUser, SysUserVO, SysUserDTO> {

    /**
     * 根据用户名查询实体
     *
     * @Author cosmos
     * @CreateTime 2019/6/14 16:30
     * @Param username 用户名
     * @Return SysUserEntity 用户实体
     */
    SysUser selectUserByName(String username);

}

