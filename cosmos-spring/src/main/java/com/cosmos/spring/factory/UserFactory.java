package com.cosmos.spring.factory;

import com.cosmos.spring.pojo.UserInfo;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.spring.factory
 * @ClassName: UserFactory
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 15:18
 * @Version: 1.0
 */
public class UserFactory extends AbstractFactoryBean<UserInfo> {

    private UserInfo userInfo;

    @Override
    public Class<?> getObjectType() {
        return UserInfo.class;
    }

    @Override
    protected UserInfo createInstance() throws Exception {
        userInfo.setPassword(userInfo.getPassword() + "   aaaaa");
        return userInfo;
    }


    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
