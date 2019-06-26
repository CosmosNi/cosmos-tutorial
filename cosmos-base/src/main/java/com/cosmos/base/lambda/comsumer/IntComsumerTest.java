package com.cosmos.base.lambda.comsumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.lambda.comsumer
 * @ClassName: IntComsumerTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/26 20:18
 * @Version: 1.0
 */
public class IntComsumerTest {

    public static void reduce(List<Integer> list, IntConsumer intConsumer) {
        for (Integer data : list) {
            intConsumer.accept(data);
        }
    }

    public static void main(String[] args) {
        IntComsumerTest.reduce(Arrays.asList(222, 3333, 4444), (int data) -> {
            System.out.println(data * 100);
        });
    }
}
