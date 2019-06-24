package com.cosmos.design.observer;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.observer
 * @ClassName: Subject
 * @Author: keda
 * @Description: 主题
 * @Date: 2019/6/21 10:50
 * @Version: 1.0
 */
public interface Subject {

    void register(Observer observer);

    void remove(Observer observer);

    void notifyObservers();
}
