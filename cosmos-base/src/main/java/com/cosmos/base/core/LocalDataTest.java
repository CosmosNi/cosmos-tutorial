package com.cosmos.base.core;

import java.time.*;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.core
 * @ClassName: LocalDataTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/28 14:58
 * @Version: 1.0
 */
public class LocalDataTest {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());

        Clock clock = Clock.offset(Clock.systemUTC(), Duration.ofDays(10));
        System.out.println(LocalDate.now(clock));
        System.out.println(LocalDate.parse("2019-01-03"));


    }
}
