package cn.xgl6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author xgl
 * @create 2020-02-12 12:52
 */
public class Client1 {
    public static void main(String[] args) {
        try {
            Socket server = new Socket(InetAddress.getLocalHost(), 8888);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter out=new PrintWriter(server.getOutputStream());
            BufferedReader userin = new BufferedReader(new InputStreamReader(System.in));

            new SendThread(out,userin,false).start();
            new ReceiveTread(server,in,out,userin).start();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
