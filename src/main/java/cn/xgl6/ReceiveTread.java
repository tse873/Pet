package cn.xgl6;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class ReceiveTread extends Thread{
    BufferedReader in ;
    ServerSocket server;
    PrintWriter out;
    BufferedReader userin;
    Socket client;
    ConcurrentHashMap<String,Integer> c = new ConcurrentHashMap();

    public ReceiveTread(ServerSocket server,BufferedReader in,PrintWriter out,BufferedReader userin,Socket client) {
        this.in = in;
        this.server = server;
        this.client = client;
        this.out = out;
        this.userin = userin;
    }

    public ReceiveTread(Socket client ,BufferedReader in,PrintWriter out,BufferedReader userin) {
        this.in = in;
        this.client = client;
        this.out = out;
        this.userin = userin;
    }


    @Override
    public void run() {
        try {
            while(true){
                String info = in.readLine();

                while(info !=null){
                    System.out.println("info=" + info);


                    String pet = StringUtils.substringAfter(info, ":");
                    System.out.println("这是分割后的" + pet);
                    if(StringUtils.isNotBlank(pet)){
                        System.out.println("此时,pet不为空");
                        if (c.get(pet) == null){
                            System.out.println(c.get(pet));
                            System.out.println("此时,集合里还没有,准备添加");
                            c.put(pet,1);
                        }else {
                            System.out.println("此时,已经存在同样宠物,数量叠加");
                            Integer num = c.get(pet);
                            num ++;
                            c.put(pet,num);
                        }
                       /* for (String key : c.keySet()) {
                            System.out.println("key=" + key);

                        }
                        for (Integer value : c.values()) {
                            System.out.println("value=" + value);
                        }*/
                        Iterator<Map.Entry<String, Integer>> entries = c.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry<String, Integer> entry = entries.next();
                            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

                        }
                    }
                    if (info.equals("\tLIST")){
                        System.out.println("此时,准备进行排序");
                        final LinkedHashMap<String, Integer> collect = c.entrySet().stream().sorted(comparingByValue(new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return Integer.valueOf(o2).compareTo(Integer.valueOf(o1));
                            }
                        })).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
                        Iterator<Map.Entry<String, Integer>> entries = collect.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry<String, Integer> entry = entries.next();
                            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

                        }
                    }




                    info = in.readLine();
                }
                if(in.readLine().equals("end")){
                    break;
                }
            }
            in.close();
            out.close();
            userin.close();
            if(client != null){
                client.close();
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
