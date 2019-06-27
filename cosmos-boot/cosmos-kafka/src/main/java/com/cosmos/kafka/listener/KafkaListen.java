package com.cosmos.kafka.listener;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

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

    @Value("${server.port}")
    private String port;

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
        System.out.println("-------------" + port + "--------------");
        cr.stream().forEach(System.out::println);
        System.out.println("-------------" + port + "-------------");
    }

}
