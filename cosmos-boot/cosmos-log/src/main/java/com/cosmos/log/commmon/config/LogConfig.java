package com.cosmos.log.commmon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.config
 * @ClassName: LogConfig
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 14:21
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties("cosmos.log")
public class LogConfig {

    private String systemId;

    private String moduleId;

    private String storageMode;
}
