package com.cosmos.shiro.controller;

import com.cosmos.shiro.core.domain.ResponseMessage;
import com.cosmos.shiro.core.service.SysMenuService;
import com.cosmos.shiro.core.service.SysRoleMenuService;
import com.cosmos.shiro.core.service.SysRoleService;
import com.cosmos.shiro.core.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 角色测试
 * @Author cosmos
 * @CreateTime 2019/6/19 11:38
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 管理员角色测试接口
     *
     * @Author cosmos
     * @CreateTime 2019/6/19 10:38
     * @Return
     */
    @GetMapping("/getAdminInfo")
    @RequiresRoles("ADMIN")
    public ResponseMessage<String> getAdminInfo() {
        return ResponseMessage.ok("这里是只有管理员角色能访问的接口");
    }

    /**
     * 用户角色测试接口
     *
     * @Author cosmos
     * @CreateTime 2019/6/19 10:38
     * @Return
     */
    @GetMapping("/getUserInfo")
    @RequiresRoles("USER")
    public ResponseMessage<String> getUserInfo() {

        return ResponseMessage.ok("这里是只有用户角色能访问的接口");
    }

    /**
     * 角色测试接口
     *
     * @Author cosmos
     * @CreateTime 2019/6/19 10:38
     * @Return
     */
    @GetMapping("/getRoleInfo")
    @RequiresRoles(value = {"ADMIN", "USER"}, logical = Logical.OR)
    @RequiresUser
    public ResponseMessage<String> getRoleInfo() {

        return ResponseMessage.ok("这里是只要有ADMIN或者USER角色能访问的接口");
    }


}
