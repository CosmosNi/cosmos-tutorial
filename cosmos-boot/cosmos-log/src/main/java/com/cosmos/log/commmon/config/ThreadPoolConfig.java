package com.cosmos.log.commmon.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @ProjectName: cosmos-server
 * @Package: com.cosmos.framework.config
 * @ClassName: ThreadPoolConfig
 * @Author: keda
 * @Description: 消费队列线程线程池
 * @Date: 2019/3/26 10:39
 * @Version: 1.0
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 消费队列线程
     *
     * @return
     */
    @Bean(value = "sysLogQueueThreadPool")
    public ExecutorService buildConsumerQueueThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("sys-log-queue-thread-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(5, 20, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }


}
