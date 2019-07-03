package com.cosmos.base.juc.thread;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 只要对象不变，即使对象的属性变了，也不影响同步
 */
public class Demo7 {
    Student student;

    public Demo7() {
        student = new Student("123", "123");
    }

    public void test() throws InterruptedException {
        synchronized (student) {
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
            student.setName("111111");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
        }
    }

    @Data
    @AllArgsConstructor
    public static class Student {
        private String name;
        private String password;
    }


    public static void main(String[] args) throws InterruptedException {
        Demo7 demo7 = new Demo7();
        new Thread(() -> {
            try {
                demo7.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                demo7.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
