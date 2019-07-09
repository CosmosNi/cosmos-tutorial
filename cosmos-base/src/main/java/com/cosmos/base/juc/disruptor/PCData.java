package com.cosmos.base.juc.disruptor;

import lombok.Data;

/**
 * 无锁模式的生产着消费者
 */
@Data
public class PCData {

    private Long value;

    private String name;
}
