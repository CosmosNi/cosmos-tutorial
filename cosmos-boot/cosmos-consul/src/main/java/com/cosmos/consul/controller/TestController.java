package com.cosmos.consul.controller;

import com.cosmos.consul.config.StudentConfig;
import com.cosmos.consul.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: consul-demo
 * @Package: com.cosmos.consuldemo.controller
 * @ClassName: TestController
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/5/1 16:41
 * @Version: 1.0
 */
@RestController
public class TestController {
    @Autowired
    private FeignService feignService;
    @Autowired
    private StudentConfig studentConfig;

    @GetMapping("/test")
    public String test() {
        System.out.println("------输出----------");
        return "1232132132";
    }

    @GetMapping("/consul")
    private String testConsul() {
        return feignService.test();
    }

    @GetMapping("/config")
    private String testConfig() {
        return studentConfig.toString();
    }
}
