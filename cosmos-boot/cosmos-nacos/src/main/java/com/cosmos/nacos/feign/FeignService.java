package com.cosmos.nacos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName: consul-demo
 * @Package: com.cosmos.consuldemo.feign
 * @ClassName: FeignService
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/5/1 16:56
 * @Version: 1.0
 */
@FeignClient("cosmos-nacos")
public interface FeignService {

    @GetMapping("test")
    String test();
}
