package com.cosmos.nacos.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ProjectName: consul-demo
 * @Package: com.cosmos.consuldemo.config
 * @ClassName: StudentConfig
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/5/2 10:04
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "student")
@Data
@ToString
public class StudentConfig {
    private String name;
    private int age;
    private String sex;

}
