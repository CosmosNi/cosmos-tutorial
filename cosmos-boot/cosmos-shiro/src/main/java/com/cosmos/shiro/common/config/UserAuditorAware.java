package com.cosmos.shiro.common.config;

import com.cosmos.shiro.common.util.ShiroUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.config
 * @ClassName: UserAuditorAware
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 16:07
 * @Version: 1.0
 */
@Configuration
public class UserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of(ShiroUtils.getUserInfo().getUsername());
    }
}
