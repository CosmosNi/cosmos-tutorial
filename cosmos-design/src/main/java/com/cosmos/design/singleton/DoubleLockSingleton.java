package com.cosmos.design.singleton;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 双重检查锁，懒汉模式
 * @Date: Create in 2018-12-25 10:36
 * @Modified By：
 */
public class DoubleLockSingleton {
    /**
     * 利用volatile实现高并发下uniqueInstance的可见性
     */
    private static volatile DoubleLockSingleton uniqueInstance;

    public DoubleLockSingleton() {
    }

    /**
     * 当线程1正在执行初始化的时候，jvm已经给对象分配地址。但同时线程2判断对象不为空的时候，
     * 直接获取对象，此时线程1可能为初始化完毕，会导致线程2获取的为初始化完成的对象。
     *
     * @return
     */
    public DoubleLockSingleton getInstance() {
        //第一次判断
        if (uniqueInstance == null) {
            //同步代码块
            synchronized (this) {
                //再次判断
                if (uniqueInstance == null) {
                    //初始化
                    uniqueInstance = new DoubleLockSingleton();
                }
            }
        }
        return uniqueInstance;
    }

}
