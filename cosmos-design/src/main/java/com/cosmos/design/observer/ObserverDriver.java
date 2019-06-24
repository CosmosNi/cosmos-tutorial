package com.cosmos.design.observer;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.observer
 * @ClassName: ObserverDriver
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/21 10:48
 * @Version: 1.0
 */
public class ObserverDriver {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        for (int i = 0; i < 10; i++) {
            Student student = new Student(i + "");
            teacher.register(student);
        }
        teacher.changeStatus("上课");

        teacher.changeStatus("下课");
    }
}
