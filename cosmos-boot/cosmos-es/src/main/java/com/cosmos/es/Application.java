package com.cosmos.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.es
 * @ClassName: Application
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 16:23
 * @Version: 1.0
 */
@SpringBootApplication
@EnableElasticsearchRepositories
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
