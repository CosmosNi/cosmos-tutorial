package com.cosmos.shiro.controller;

import com.cosmos.shiro.core.domain.ResponseMessage;
import com.cosmos.shiro.core.service.SysMenuService;
import com.cosmos.shiro.core.service.SysRoleMenuService;
import com.cosmos.shiro.core.service.SysRoleService;
import com.cosmos.shiro.core.service.SysUserService;
import com.cosmos.shiro.core.vo.SysMenuVO;
import com.cosmos.shiro.core.vo.SysRoleVO;
import com.cosmos.shiro.core.vo.SysUserVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 权限测试
 * @Author cosmos
 * @CreateTime 2019/6/19 11:38
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 获取用户信息集合
     *
     * @Author cosmos
     * @CreateTime 2019/6/19 10:36
     * @Return
     */
    @GetMapping("/getUserInfoList")
    @RequiresPermissions("sys:user:info")
    public ResponseMessage<List<SysUserVO>> getUserInfoList() {

        List<SysUserVO> sysUserEntityList = sysUserService.list();

        return ResponseMessage.ok(sysUserEntityList);
    }

    /**
     * 获取角色信息集合
     *
     * @Author cosmos
     * @CreateTime 2019/6/19 10:37
     * @Return Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               Object> 返回结果
     */
    @GetMapping("/getRoleInfoList")
    @RequiresPermissions("sys:role:info")
    public ResponseMessage<List<SysRoleVO>> getRoleInfoList() {

        List<SysRoleVO> sysRoleList = sysRoleService.list();

        return ResponseMessage.ok(sysRoleList);
    }

    /**
     * 获取权限信息集合
     *
     * @Author cosmos
     * @CreateTime 2019/6/19 10:38
     * @Return Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               Object>
     */
    @GetMapping("/getMenuInfoList")
    @RequiresPermissions("sys:menu:info")
    public ResponseMessage<List<SysMenuVO>> getMenuInfoList() {

        List<SysMenuVO> sysMenuEntityList = sysMenuService.list();

        return ResponseMessage.ok(sysMenuEntityList);
    }
}