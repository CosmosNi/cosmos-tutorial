package com.cosmos.base.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.core
 * @ClassName: ReflectDemo
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/28 15:54
 * @Version: 1.0
 */
public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class clazz = Class.forName("com.cosmos.base.core.Person");
        //获取所有有访问权限方法
        //Arrays.asList(clazz.getMethods()).stream().forEach(System.out::println);

        Person person = (Person) clazz.getConstructor(String.class, String.class).newInstance("张三", "123333");
        //类加载器
        //System.out.println(clazz.getClassLoader());
        //获取所有方法
        //Arrays.asList(clazz.getDeclaredMethods()).stream().forEach(System.out::println);

        Arrays.asList(clazz.getDeclaredFields()).stream().forEach(System.out::println);

        System.out.println(clazz.getSuperclass());

        Person person1 = new Person("123", "1233");
        Class clazz1 = person1.getClass();
        Field field = clazz1.getDeclaredField("username");
        //允许访问私有变量
        field.setAccessible(true);
        System.out.println(field.get(person1));

        Method method = clazz1.getMethod("getUsername", null);
        System.out.println(method.invoke(person1));
    }


}
