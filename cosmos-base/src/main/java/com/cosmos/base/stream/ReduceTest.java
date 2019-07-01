package com.cosmos.base.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.stream
 * @ClassName: ReduceTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/1 15:34
 * @Version: 1.0
 */
public class ReduceTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1111);
        list.add(2222);
        list.add(3333);
        list.add(4444);
        list.add(5555);
        System.out.println(list.stream().reduce(BinaryOperator.maxBy(Comparator.comparing(s -> s))).get());
        System.out.println(list.stream().reduce((integer, integer2) -> integer + integer2).get());


        //集合转换成IntStream
        System.out.println(list.stream().flatMapToInt(s -> IntStream.of(s)).sum());
        IntStream stream = IntStream.of(1, 2, 3, 5, 7);
        //转换成list
       //stream.boxed().collect(Collectors.toList());
        System.out.println(stream.average().getAsDouble());
    }
}
