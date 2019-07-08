package com.cosmos.spring;


import com.cosmos.spring.pojo.UserInfo;
import com.cosmos.spring.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.spring
 * @ClassName: Demo1
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 14:33
 * @Version: 1.0
 */
public class Demo1 {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserInfo userInfo = (UserInfo) applicationContext.getBean("user");
        System.out.println(userInfo.toString());

        UserInfo userInfo2 = (UserInfo) applicationContext.getBean("user2");
        System.out.println(userInfo2.toString());

        UserInfo userInfo3 = (UserInfo) applicationContext.getBean("user3");
        System.out.println(userInfo3.toString());

        UserInfo userInfo4 = (UserInfo) applicationContext.getBean("user4");
        System.out.println(userInfo4.toString());

        TestService demo1 = (TestService) applicationContext.getBean("testService");
        demo1.read();

    }
}
