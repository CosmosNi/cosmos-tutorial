package com.cosmos.demo.controller;

import com.cosmos.demo.annotation.JsonParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.demo.controller
 * @ClassName: TestController
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/28 10:10
 * @Version: 1.0
 */
@RestController
public class TestController {
    @PostMapping("/test")
    public String test(@JsonParam("user") String username, @JsonParam("password") String password) {
        return "username:" + username + "  password:" + password;
    }
}
