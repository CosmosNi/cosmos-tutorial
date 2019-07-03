package com.cosmos.base.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.socket
 * @ClassName: ServerSocketTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/2 13:50
 * @Version: 1.0
 */
public class ServerSocketTest extends Thread {

    private ServerSocket serverSocket;

    public ServerSocketTest(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        //serverSocket.setSoTimeout(10000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                server.setKeepAlive(true);
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port = 8098;
        try {
            Thread t = new ServerSocketTest(port);
            t.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
