package com.cosmos.design.singleton;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.singleton
 * @ClassName: StaticSingleton
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 23:21
 * @Version: 1.0
 */
public class StaticSingleton {
    private static StaticSingleton staticSingleton = null;

    static {
        staticSingleton = new StaticSingleton();
    }

    public StaticSingleton() {
    }

    public StaticSingleton getInstance() {
        return staticSingleton;
    }
}
