package com.cosmos.base.lambda.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.lambda.predicate
 * @ClassName: IntPredicateTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/26 20:12
 * @Version: 1.0
 */
public class IntPredicateTest {

    public static List<Integer> filter(List<Integer> list, IntPredicate intPredicate) {
        List<Integer> filterData = new ArrayList<>();
        for (Integer data : list) {
            if (intPredicate.test(data)) {
                filterData.add(data);
            }
        }
        return filterData;
    }

    public static void main(String[] args) {
        List<Integer> list = IntPredicateTest.filter(Arrays.asList(1, 2, 3, 6), (int data) -> data % 2 == 0);
        list.stream().forEach(System.out::println);

    }
}
