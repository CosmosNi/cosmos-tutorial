package com.cosmos.log.commmon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.config
 * @ClassName: UserAuditorAware
 * @Author: keda
 * @Description: 该类需要用户自己制定
 * @Date: 2019/6/6 16:07
 * @Version: 1.0
 */
@Configuration

public class UserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of("anonymity");
    }
}
