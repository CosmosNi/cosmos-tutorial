package com.cosmos.shiro.controller;

import com.cosmos.shiro.common.util.ShiroUtils;
import com.cosmos.shiro.core.domain.ResponseMessage;
import com.cosmos.shiro.core.dto.SysUserDTO;
import com.cosmos.shiro.core.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户登录
 * @Author cosmos
 * @CreateTime 2019/6/17 15:21
 */
@RestController
@RequestMapping("/userLogin")
@Api(tags = "登录控制器", description = "用户登录登出")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     *
     * @Author cosmos
     * @CreateTime 2019/6/20 9:21
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录请求")
    public ResponseMessage<String> login(@RequestBody SysUserDTO sysUserDTO) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken
                (sysUserDTO.getUsername(), sysUserDTO.getPassword());
        //验证成功进行登录操作
        subject.login(token);


        return ResponseMessage.ok(ShiroUtils.getSession().getId().toString());
    }

    /**
     * 登出
     *
     * @Author cosmos
     * @CreateTime 2019/6/19 10:38
     * @Return
     */
    @GetMapping("/getLogout")
    @RequiresUser
    @ApiOperation(value = "登出请求")
    public ResponseMessage<String> getLogout() {
        ShiroUtils.logout();
        return ResponseMessage.ok("登出成功！");
    }

    /**
     * 未登录
     *
     * @Author cosmos
     * @CreateTime 2019/6/20 9:22
     */
    @GetMapping("/unauth")
    public ResponseMessage<String> unauth() {

        return ResponseMessage.error("用户未登录！");
    }
}