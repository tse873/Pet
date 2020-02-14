package com.pet.server;

import com.pet.enums.PetEnums;

import java.io.*;
import java.net.Socket;

public class Chanel extends Thread  {

    //和本线程相关的socket
    Socket socket = null;

    public Chanel(Socket socket){
        this.socket = socket;
    }

    //线程执行的操作，响应客户端的请求
    public void run(){
        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter writer = null;
        try{
            //获取输入流，并读取客户端信息
            inputStream = socket.getInputStream();
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String info = null;
            //循环读取客户端的信息
            String message = null;
            while ((info = bufferedReader.readLine()) != null){
                System.out.println("我是服务器，客户端说：" + info);
                message = info;
            }
            //关闭输入流
            socket.shutdownInput();
            //获取输出流，响应客户端的请求
            outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream);
            if("LIST".equals(message)){
                writer.write(PetEnums.getList());
                writer.write("OK");
            }else if(message.startsWith("GET:")){
                //数量+1
                String petName = message.split(":")[1].toLowerCase();
                int num = PetEnums.getNum(petName) + 1;
                PetEnums.setNum(petName, num);
                writer.write("OK");
            }else{
                writer.write("请重新输入：");
                writer.write("领养宠物请求，可供收养的动物有狗(dog)，猫(cat)，鹦鹉(chicken)，小鸡(parrot)等。比如领养一只小狗，客户端可以发送：GET:dog");
                writer.write("查询宠物领养数量。客户端可以发送：LIST");
            }
            //调用flush()方法将缓冲输出
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //关闭资源
            try{
                if(writer != null)
                    writer.close();
                if(outputStream != null)
                    outputStream.close();
                if(bufferedReader != null)
                    bufferedReader.close();
                if(reader != null)
                    reader.close();
                if(inputStream != null)
                    inputStream.close();
                if(socket != null)
                    socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

}
