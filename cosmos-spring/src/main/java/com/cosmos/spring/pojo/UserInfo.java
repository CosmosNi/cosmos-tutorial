package com.cosmos.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.spring.pojo
 * @ClassName: UserInfo
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 14:39
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    private String name;

    private String password;

    private List<String> list;

    private List<UserInfo> parents;

    public UserInfo(String name, String password, List<String> list) {
        this.name = name;
        this.password = password;
        this.list = list;
    }
}
