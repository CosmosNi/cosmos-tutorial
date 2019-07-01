package com.cosmos.base.stream;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.stream
 * @ClassName: Demo2
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/1 14:26
 * @Version: 1.0
 */
public class Demo2 {

    public static void main(String[] args) {
        //生成随机无限流
       //Stream.generate(Math::random).limit(1000).forEach(System.out::println);
        Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE)).peek(System.out::println).limit(1000);
        //创建一个空流
        Stream.empty();
    }
}
