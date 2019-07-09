package com.cosmos.base.juc.disruptor;

import com.lmax.disruptor.RingBuffer;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.disruptor
 * @ClassName: Producer
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/9 15:19
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class Producer {

    private final RingBuffer<PCData> ringBuffer;

    public void pushData(Long value, String name) {
        long sequence = ringBuffer.next();
        PCData event = ringBuffer.get(sequence);
        event.setValue(value);
        event.setName(name);
        ringBuffer.publish(sequence);
    }

}
