package com.cosmos.base.lambda.function;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.lambda.function
 * @ClassName: BiFunctionTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/26 20:25
 * @Version: 1.0
 */
public class BiFunctionTest {

    public static <T, U, R> List<R> transform(List<T> list, List<U> list2, BiFunction<T, U, R> function) {
        List<R> collect = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            collect.add(function.apply(list.get(i), list2.get(i)));
        }
        return collect;
    }

    public static void main(String[] args) {
        BiFunctionTest.transform(Arrays.asList(123, 123, 123),
                Arrays.asList(234, 2345, 23456),
                (Integer i, Integer j) -> i.intValue() + j.intValue()).stream().forEach(System.out::println);

    }
}
