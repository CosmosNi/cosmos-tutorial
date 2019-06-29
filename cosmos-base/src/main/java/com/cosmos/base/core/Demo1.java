package com.cosmos.base.core;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.core
 * @ClassName: Demo1
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/28 15:17
 * @Version: 1.0
 */
public class Demo1 {

    {
        System.out.println("--------初始化代码块-------");
    }

    static {
        System.out.println("----------静态代码块-------------");
    }

    public Demo1() {
        System.out.println("---------构造函数------------ ");
    }


    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
    }
}
