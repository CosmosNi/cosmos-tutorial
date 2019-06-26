package com.cosmos.base.lambda.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.lambda.function
 * @ClassName: IntToDoubleFunctionTest
 * @Author: keda
 * @Description:   int类型转换成double类型
 * @Date: 2019/6/26 17:11
 * @Version: 1.0
 */
public class IntToDoubleFunctionTest {

    public static List map(List<Integer> list, IntToDoubleFunction function) {
        List<Double> result = new ArrayList<>();

        for (Integer t : list) {
            result.add(function.applyAsDouble(t.intValue()));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Double> list = map(Arrays.asList(2222, 3333, 4444), (int s) -> s + 0.01);
        list.stream().forEach(System.out::println);
    }
}
