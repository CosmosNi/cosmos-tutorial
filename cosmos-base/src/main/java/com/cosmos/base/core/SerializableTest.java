package com.cosmos.base.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.core
 * @ClassName: SerializableTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/2 13:15
 * @Version: 1.0
 */
public class SerializableTest {

    public static void main(String[] args) {
        SerializableTest.write();
        SerializableTest.read();
    }

    //序列化
    public static void write() {
        try {
            User user = new User("zhangsan", "123456");
            FileOutputStream fileOutputStream = new FileOutputStream("D://test.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //反序列化
    public static void read() {
        try {
            FileInputStream fis = new FileInputStream("D://test.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println(ois.readObject());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User implements Serializable {
        private static final long serialVersionUID = -1L;
        private String name;
        private String password;

        public static String type= "222";

        private void writeObject(ObjectOutputStream s) throws IOException {
            System.out.println("开始序列化");
            s.defaultWriteObject();

        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            System.out.println("读取序列");
            s.defaultReadObject();

        }
    }
}
