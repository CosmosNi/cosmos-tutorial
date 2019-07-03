package com.cosmos.base.socket;

import java.io.*;
import java.net.Socket;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.socket
 * @ClassName: SocketClientTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/2 14:05
 * @Version: 1.0
 */
public class SocketClientTest {
    public static void main(String[] args) {
        new Thread(() -> {
            String serverName = "127.0.0.1";
            int port = 8098;
            try {
                System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
                Socket client = new Socket(serverName, port);
                client.setKeepAlive(true);

                System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                out.writeUTF("Hello from " + client.getLocalSocketAddress());
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("服务器响应： " + in.readUTF());

            } catch (IOException e) {
                e.printStackTrace();

            }
        }).start();


    }
}
