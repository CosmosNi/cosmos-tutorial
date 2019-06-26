package com.cosmos.base.lambda.function;

import java.util.function.Function;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.lambda
 * @ClassName: FunctionTest
 * @Author: keda
 * @Description: function 将某个对象转换成另外一个对象的过程
 * @Date: 2019/6/26 16:48
 * @Version: 1.0
 */
public class FunctionTest {

    public static Student transform(Student student, Function<Student, Student> function) {
        Student stu = function.apply(student);
        return stu;

    }




    public static void main(String[] args) {
        Student student = new Student();
        student.setName("张三");
        student.setPassword("123456");
        FunctionTest.transform(student, (Student s) -> {
            s.setPassword("4444444");
            return s;
        });
        System.out.println(student.toString());


    }

    public static class Student {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
