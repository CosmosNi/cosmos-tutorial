package com.cosmos.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.observer
 * @ClassName: Teacher
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/21 10:51
 * @Version: 1.0
 */
public class Teacher implements Subject {

    private List<Observer> studentList = new ArrayList<Observer>();

    private String status;

    @Override
    public void register(Observer observer) {
        System.out.println("学生：" + observer.getUsername() + "加入群聊");
        studentList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        studentList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : studentList) {
            observer.updateStatus(status);
        }
    }

    /**
     * 更改老师上课状态
     *
     * @param status
     */
    public void changeStatus(String status) {
        this.status = status;
        notifyObservers();
    }
}
