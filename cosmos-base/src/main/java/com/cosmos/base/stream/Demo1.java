package com.cosmos.base.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.stream
 * @ClassName: Demo1
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/1 13:54
 * @Version: 1.0
 */
public class Demo1 {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("aaa");
        list1.add("bbbb");
        list1.add("ccccc");
        List<String> list2 = new ArrayList<>();
        list1.add("aaa");
        list1.add("bbbb");
        list1.add("ddddd");

        List<String> newList = Stream.of(list1, list2)
                //将两个集合整合成一个流
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(newList.size());


    }
}
