package com.cosmos.design.singleton;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 饿汉模式
 * @Date: Create in 2018-12-25 10:43
 * @Modified By：
 */
public class StarvationSingleton {
    private static StarvationSingleton uniqueInstance = new StarvationSingleton();

    public StarvationSingleton() {
    }

    public static StarvationSingleton getInstance() {
        return uniqueInstance;
    }
}
