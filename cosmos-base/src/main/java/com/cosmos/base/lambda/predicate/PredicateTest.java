package com.cosmos.base.lambda.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ProjectName: cosmos-stream
 * @Package: com.cosmos.lambda.simple
 * @ClassName: PredicateTest
 * @Author: keda
 * @Description: jdk自带的验证器，用来返回一个boolean类型的值
 * @Date: 2019/1/13 15:09
 * @Version: 1.0
 */
public class PredicateTest {

    public static List<String> filer(List<String> list, Predicate<String> predicate) {
        List<String> testList = new ArrayList<>();
        for (String test : list) {
            if (predicate.test(test)) {
                testList.add(test);
            }
        }
        return testList;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {
            {
                add("aaa");
                add("aaa");
                add("bbb");
            }
        };
        list = filer(list, (String s) -> s.equals("aaa"));
        list.stream().forEach(System.out::println);


    }
}
