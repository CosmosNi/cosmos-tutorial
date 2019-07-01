package com.cosmos.base.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.stream
 * @ClassName: ParallelStreamTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/1 16:07
 * @Version: 1.0
 */
public class ParallelStreamTest {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("1", "1"));
        list.add(new User("1", "2"));
        list.add(new User("2", "3"));
        list.add(new User("2", "4"));
        list.add(new User("5", "5"));

        Map<String, List<User>> map = list.parallelStream()
                .collect(Collectors.groupingBy(User::getName, Collectors.toList()));
        map.forEach((key, data) -> System.out.println("key:" + key + "  value:" + data));

    }

    public static class User {
        private String name;

        private String password;

        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

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
            return "User{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
