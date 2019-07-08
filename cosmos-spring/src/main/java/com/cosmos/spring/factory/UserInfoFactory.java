package com.cosmos.spring.factory;

import com.cosmos.spring.pojo.UserInfo;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.spring.factory
 * @ClassName: UserInfoFactory
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 14:40
 * @Version: 1.0
 */
public class UserInfoFactory implements FactoryBean<UserInfo> {

    private String userInfo;


    /**
     *返回由factoryBean创建的bean实例，如果isSingleton()返回true
     * ,则该实例会放到spring容器中单实例缓存池中
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo getObject() throws Exception {
        UserInfo user = new UserInfo();
        String[] infos = userInfo.split(",");
        user.setName(infos[0]);
        user.setPassword(infos[1]);
        return user;
    }

    //bean的作用域
    @Override
    public Class<?> getObjectType() {
        return UserInfo.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

}