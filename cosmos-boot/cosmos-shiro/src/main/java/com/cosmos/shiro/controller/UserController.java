package com.cosmos.shiro.controller;

import com.cosmos.shiro.core.domain.ResponseMessage;
import com.cosmos.shiro.core.dto.SysUserDTO;
import com.cosmos.shiro.core.service.SysUserService;
import com.cosmos.shiro.core.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.shiro.controller
 * @ClassName: UserController
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/24 15:35
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/save")
    public ResponseMessage<String> saveUser(@RequestBody SysUserDTO sysUserDTO) {
        sysUserService.saveDTO(sysUserDTO);
        return ResponseMessage.ok("用户新增成功！");
    }

    @DeleteMapping("/delete")
    public ResponseMessage<String> deleteUser(Long id) {

        sysUserService.delete(id);
        return ResponseMessage.ok("用户" + id + "删除成功！");
    }

    @GetMapping("/list")
    public ResponseMessage<List<SysUserVO>> getUserList() {
        return ResponseMessage.ok(sysUserService.list());
    }
}
