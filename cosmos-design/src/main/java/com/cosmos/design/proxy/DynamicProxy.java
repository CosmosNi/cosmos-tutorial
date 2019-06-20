package com.cosmos.design.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.design.proxy
 * @ClassName: DynamicProxy
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/20 16:38
 * @Version: 1.0
 */
public class DynamicProxy implements InvocationHandler, MethodInterceptor {

    private Object object;

    public DynamicProxy() {
    }

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---------执行代理------------");
        Object result = method.invoke(object, args);
        System.out.println("代理结果：" + result);
        System.out.println("---------代理结束------------");
        return null;
    }

    /**
     * 相当于JDK动态代理中的绑定
     */
    public Object getInstance(Object target) {
        //给业务对象赋值
        this.object = target;
        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(this.object.getClass());
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    /**
     * cglib 代理拦截
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("预处理——————");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println(result);
        System.out.println("调用后操作——————");
        return result;
    }

    //jdk代理
    static People getPersonProxy(People people) {
        return (DynamicProxy.People) Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(), new DynamicProxy(people));
    }

    //Cglib代理
    static People getCglibProxy(People people) {
        return (People) new DynamicProxy().getInstance(people);
    }

    private interface People {
        String doOperation();
    }

    public static class Student implements People {

        @Override
        public String doOperation() {
            return "学生正在上课！";
        }
    }


    public static void main(String[] args) {
        People people = new Student();
        People proxyPeople = getPersonProxy(people);
        proxyPeople.doOperation();

        People cglibPeople = getCglibProxy(people);
        cglibPeople.doOperation();
    }
}
