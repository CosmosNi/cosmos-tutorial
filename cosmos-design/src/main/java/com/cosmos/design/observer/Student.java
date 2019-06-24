package com.cosmos.design.observer;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.observer
 * @ClassName: Student
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/21 10:56
 * @Version: 1.0
 */
public class Student implements Observer {
    private String username;


    @Override
    public void updateStatus(String status) {
        System.out.println("学生:" + username + "开始：" + status);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Student(String username) {
        this.username = username;
    }
}
