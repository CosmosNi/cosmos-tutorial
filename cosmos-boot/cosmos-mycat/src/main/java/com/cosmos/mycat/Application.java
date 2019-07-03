package com.cosmos.mycat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.shiro
 * @ClassName: Application
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/24 9:10
 * @Version: 1.0
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(enableDefaultTransactions = false)
@EntityScan
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
