package com.cosmos.base.lambda.comsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @ProjectName: cosmos-stream
 * @Package: com.cosmos.lambda.simple
 * @ClassName: ConsumerTest
 * @Author: keda
 * @Description: java自带Consumer，用来执行指定的void方法
 * @Date: 2019/1/13 15:16
 * @Version: 1.0
 */
public class ConsumerTest {

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static void print(String t) {
        System.out.println(t);
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {
            {
                add("aaa");
                add("aaa");
                add("bbb");
            }
        };
        forEach(list, (String s) -> System.out.println(s));
        forEach(list, System.out::println);
        forEach(list, ConsumerTest::print);
    }
}
