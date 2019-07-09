package com.cosmos.base.juc.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.disruptor
 * @ClassName: PCDataFactory
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/9 15:17
 * @Version: 1.0
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
