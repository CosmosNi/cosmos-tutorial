package com.cosmos.nacos.service;

import com.cosmos.nacos.config.StudentConfig;
import com.cosmos.nacos.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: cosmos-nacos
 * @Package: com.cosmos.nacos.service
 * @ClassName: TestService
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/5/6 12:32
 * @Version: 1.0
 */
@Service
@RefreshScope
public class TestService {

    @Autowired
    private FeignService feignService;
    @Autowired
    private StudentConfig studentConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${server.port}")
    private String port;


    public String test() {
        System.out.println("进入测试方法");
        return "test";
    }

    /**
     * fegin方式远程调用
     *
     * @return
     */
    public String testNacos() {

        return feignService.test();
    }

    /**
     * 读取配置中心
     *
     * @return
     */
    public String testConfig() {
        System.out.println(studentConfig.toString());
        return studentConfig.getName();
    }

    /**
     * restTemplate方式调用
     *
     * @return
     */
    public String testTest() {
        return restTemplate.getForObject("http://cosmos-nacos/test/", String.class);
    }
}
