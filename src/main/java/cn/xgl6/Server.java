package cn.xgl6;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xgl
 * @create 2020-02-12 11:12
 */
public class Server {
    public static void main(String[] args) {
       /* try {
            ServerSocket ss = new ServerSocket(8080);
            System.out.println("启动服务器....");
            Socket s = ss.accept();
            System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("客户端："+mess);
            ConcurrentHashMap<String,Integer> c = new ConcurrentHashMap();
            String pet = StringUtils.substringAfter(mess, ":");
            if (c.get(pet) == null){
                c.put(pet,1);
            }else {
                Integer num = c.get(pet);
                num ++;
                c.put(pet,num);
            }
            for (String key : c.keySet()) {
                System.out.println("key=" + key);

            }
            for (Integer value : c.values()) {
                System.out.println("value=" + value);
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write("OK");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            Socket s = ss.accept();
            System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("客户端："+mess);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write("OK\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
