package com.pet.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 宠物领送系统服务端
 * */
public class Server {

    public static void main(String[] args) {
        //记录客户端的数量
        int num = 0;
        //创建服务器
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(8888);
            System.out.println("宠物领送系统服务端已启动，端口号--->8888");
        }catch (IOException e){
            e.printStackTrace();
        }
        while(true){
            //接收客户端
            try {
                socket = serverSocket.accept();
                //创建一个新的线程
                Chanel serverThread = new Chanel(socket);
                //开启线程
                serverThread.start();
                num ++;
                System.out.println("客户端的数量：" + num);
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP：" + address.getHostAddress());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
