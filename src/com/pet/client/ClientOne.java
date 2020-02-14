package com.pet.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端1
 * */
public class ClientOne {

    public static void main(String[] args) {
        System.out.println("领养宠物请求，可供收养的动物有狗(dog)，猫(cat)，鹦鹉(chicken)，小鸡(parrot)等。比如领养一只小狗，客户端可以发送：GET:dog");
        System.out.println("查询宠物领养数量。客户端可以发送：LIST");

        while (true){
            try{
                //创建客户端socket，指定服务器地址和端口
                Socket socket = new Socket("localhost", 8888);
                //获取输出流，向服务端发送信息
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                Scanner scanner = new Scanner(System.in);
                String message = scanner.next();
                printWriter.write(message);
                printWriter.flush();
                //关闭输出流
                socket.shutdownOutput();
                //获取输入流，并读取服务器端的响应信息
                InputStream is=socket.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String info = null;
                while((info = br.readLine()) != null){
                    System.out.println(info);
                }
                //关闭资源
                br.close();
                is.close();
                printWriter.close();
                outputStream.close();
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }

}