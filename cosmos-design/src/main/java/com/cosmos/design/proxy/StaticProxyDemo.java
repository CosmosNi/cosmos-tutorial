package com.cosmos.design.proxy;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.proxy
 * @ClassName: StaticProxyDemo
 * @Author: keda
 * @Description: 静态代理
 * @Date: 2019/6/20 16:56
 * @Version: 1.0
 */
public class StaticProxyDemo {


    interface Student {
        void execute();
    }

    public static class StudentImpl implements Student {

        @Override
        public void execute() {
            System.out.println("--------执行方法----------");
        }
    }

    public static class StudentProxy implements Student {
        private Student student;

        public StudentProxy(Student student) {
            this.student = student;
        }

        @Override
        public void execute() {
            System.out.println("-------代理--------");
            student.execute();
            System.out.println("-------代理完毕--------");
        }
    }

    public static void main(String[] args) {
        new StudentProxy(new StudentImpl()).execute();
    }
}
