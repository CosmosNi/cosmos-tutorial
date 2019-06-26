package com.cosmos.kafka.listener;

import com.cosmos.kafka.domain.User;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.kafka.listener
 * @ClassName: KafkaListen
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/26 10:31
 * @Version: 1.0
 */
@Configuration
public class KafkaListen {

    @Bean
    public NewTopic topic1() {
        return new NewTopic("user", 10, (short) 2);
    }
//
//    @KafkaListener(id = "user", topics = "user")
//    public void listen(List<User> users) {
//        System.out.println("-------------------------------");
//        users.stream().forEach(System.out::println);
//        System.out.println("-------------------------------");
//    }

    @KafkaListener(id = "user", topics = "user")
    public void listen(List<ConsumerRecord<?, ?>> cr) {
        System.out.println("-------------------------------");
        cr.stream().forEach(System.out::println);
        System.out.println("-------------------------------");
    }
}
