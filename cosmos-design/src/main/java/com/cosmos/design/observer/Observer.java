package com.cosmos.design.observer;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.observer
 * @ClassName: Observer
 * @Author: keda
 * @Description: 观察者
 * @Date: 2019/6/21 10:48
 * @Version: 1.0
 */
public interface Observer {

    void updateStatus(String status);

    String getUsername();
}
