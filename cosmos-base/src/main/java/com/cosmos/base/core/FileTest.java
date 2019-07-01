package com.cosmos.base.core;

import java.io.*;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.core
 * @ClassName: FileTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/1 16:39
 * @Version: 1.0
 */
public class FileTest {

    public static void write() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = new PrintWriter("D://test.txt", "UTF-8");
        pw.print("测试文本");
        pw.append("拼接");
        pw.close();
    }

    public static void read() throws IOException {
        FileInputStream fis = new FileInputStream("D://test.txt");
        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        StringBuilder sb = new StringBuilder();
        int b;
        while ((b = isr.read()) != -1) {
            sb.append((char) b);
        }
        fis.close();
        isr.close();
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {

        FileTest.write();
        FileTest.read();
    }
}
