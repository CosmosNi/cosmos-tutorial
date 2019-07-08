package com.cosmos.spring.service;

import com.cosmos.spring.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.spring.service
 * @ClassName: TestService
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 16:09
 * @Version: 1.0
 */
@Component
public class TestService {

    @Qualifier("user2")
    @Autowired
    private UserInfo userInfo;

    public void read() {
        System.out.println(" 自动装配" + userInfo.toString());
    }

}
