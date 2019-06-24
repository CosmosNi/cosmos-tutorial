package com.cosmos.nacos.controller;

import com.cosmos.nacos.service.TestService;
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
    private TestService testService;


    @GetMapping("/test")
    public String test() {

        return testService.test();
    }

    /**
     * fegin方式远程调用
     *
     * @return
     */
    @GetMapping("/feign")
    public String testNacos() {

        return testService.testNacos();
    }

    /**
     * 读取配置中心
     *
     * @return
     */
    @GetMapping("/config")
    public String testConfig() {
        return testService.testConfig();
    }

    /**
     * restTemplate方式调用
     *
     * @return
     */
    @GetMapping("/rest/template")
    public String testTest() {
        return testService.testTest();
    }
}
