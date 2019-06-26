package com.cosmos.base.lambda.operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.lambda.operator
 * @ClassName: OperatorTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/26 20:50
 * @Version: 1.0
 */
public class OperatorTest {

    public static List<Integer> getReult(List<Integer> list, IntUnaryOperator intUnaryOperator) {
        List<Integer> result = new ArrayList<>();
        for (Integer data : list) {
            result.add(intUnaryOperator.applyAsInt(data));
        }
        return result;
    }

    public static List<Integer> getReults(List<Integer> list, List<Integer> list2, IntBinaryOperator intBinaryOperator) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(intBinaryOperator.applyAsInt(list.get(i), list2.get(i)));
        }
        return result;
    }


    public static void main(String[] args) {
        OperatorTest.getReult(Arrays.asList(1, 2, 3, 4), (s) -> s * 100).stream().forEach(System.out::println);
        OperatorTest.getReults(Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 2, 3, 4), (s, t) -> s * t).stream().forEach(System.out::println);
    }
}
