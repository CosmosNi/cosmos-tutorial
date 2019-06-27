package com.cosmos.kafka.controller;

import com.cosmos.kafka.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.kafka.controller
 * @ClassName: TestController
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/26 10:43
 * @Version: 1.0
 */
@RestController
public class TestController {
    @Autowired
    private KafkaTemplate<Object, Object> template;

    @GetMapping("/send")
    public void sendMessage() {
        for (int i = 0; i < 10; i++) {
            User u = new User();
            u.setUsername("学生" + i);
            u.setPassword("666666");
            template.send("user", u);
        }
    }
}
