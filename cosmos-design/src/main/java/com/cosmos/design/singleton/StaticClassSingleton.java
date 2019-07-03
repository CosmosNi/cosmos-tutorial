package com.cosmos.design.singleton;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.singleton
 * @ClassName: StaticClassSingleton
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 23:19
 * @Version: 1.0
 */
public class StaticClassSingleton {

    public static class singletonHandler {
        private static StaticClassSingleton staticSingleton = new StaticClassSingleton();
    }

    public StaticClassSingleton getInstance() {
        return singletonHandler.staticSingleton;
    }
}
