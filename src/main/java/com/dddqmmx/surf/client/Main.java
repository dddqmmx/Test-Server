package com.dddqmmx.surf.client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Socket ss = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bosFile = null;    // 与输出文件流相关联
        try {
            ss = new Socket("127.0.0.1", 2042);
            bis = new BufferedInputStream(ss.getInputStream());
            // 等待接收服务器发送回来的消息
            while(true) {
                byte[] by = new byte[1024+2];
                int res = bis.read(by);
                int sendUser = by[0];
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                String format = sdf.format(date);
                if (by[1] == 1) // 说明传的是文件
                {
                    bosFile = new BufferedOutputStream(new FileOutputStream("./directoryTest/用户" + sendUser + "-传输的文件.png", true));
                    bosFile.write(by, 2, res-2);
                    bosFile.flush();
                    if (res<1026)   // 说明是最后一次在传送文件，所以传送的字节数才会小于字节数组by的大小
                    {
                        //System.out.println("客户端接收到的信息" + receive);
                        System.out.println("用户" + sendUser + "\t" + format + ":");
                        System.out.printf("用户%d发送的文件传输完成\n", sendUser);
                    }
                }
                else    // 说明传输的是聊天内容，则按字符串的形式进行解析
                {
                    // 利用String构造方法的形式，将字节数组转化成字符串打印出来
                    StringBuffer sb = new StringBuffer();;
                    for (int i = 0; i < by.length; i++)
                    {
                        sb.append("{"+by[i]+"},");

                    }
                    System.out.println(sb);
                    String receive = new String(by, 2, res);
                    System.out.println("用户" + sendUser + "\t" + format + ":");
                    System.out.println(receive);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
