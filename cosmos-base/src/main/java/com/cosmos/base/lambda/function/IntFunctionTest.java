package com.cosmos.base.lambda.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.lambda
 * @ClassName: IntFunctionTest
 * @Author: keda
 * @Description: 将int类型转换成其他类型
 * @Date: 2019/6/26 17:09
 * @Version: 1.0
 */
public class IntFunctionTest {
    public static <R> List map(List<Integer> list, IntFunction<R> function) {
        List<R> result = new ArrayList<>();

        for (Integer t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = map(Arrays.asList(2222, 3333, 4444), (int s) -> s * 100);
        list.stream().forEach(System.out::println);
    }
}
