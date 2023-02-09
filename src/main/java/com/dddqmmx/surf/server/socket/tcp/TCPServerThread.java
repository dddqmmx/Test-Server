package com.dddqmmx.surf.server.socket.tcp;

import com.dddqmmx.surf.server.util.MessageUtil;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TCPServerThread extends Thread{

    //被占用的的messageId
    public byte[] occupiedMessageId = new byte[256];
    /*字节的最大上限如果发送消息的时候messageId等于maxMessageId则把message设置为minMessageId
    因为以后可能会改成int,long啥的就把max和min写上了
     */
    public byte maxMessageId = 127;
    //字节的最小上限;
    public byte minMessageId = -128;
    //存储下个message的
    public byte messageId = -128;

    private Socket socket = null;
    private String sessionId = null;
    private BufferedOutputStream bufferedOutputStream = null;
    private BufferedInputStream bufferedInputStream = null;

    public static Map<Byte,ByteArrayOutputStream> byteArrayOutputStreamMap = new HashMap<>();

    public TCPServerThread(Socket socket,String sessionId) {
        this.socket = socket;
        this.sessionId = sessionId;
    }

    @Override
    public void run() {
        try {
            bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            send("烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希烂裤裆後藤希123");
            int o = 0;
            while(true) {
                byte[] by = new byte[1024+3];
                int res = 0;
                try {
                    res = bufferedInputStream.read(by);
                    if (by[1]==1){
                        byte messageId = by[0];
                        // 利用String构造方法的形式，将字节数组转化成字符串打印出来
                        if (byteArrayOutputStreamMap.containsKey(messageId)){
                            ByteArrayOutputStream byteArrayOutputStream = byteArrayOutputStreamMap.get(messageId);
                            byteArrayOutputStream.write(by,3,res-3);
                            byteArrayOutputStream.flush();
                            byteArrayOutputStream.close();
                        }else{
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byteArrayOutputStream.write(by,3,res-3);
                            byteArrayOutputStream.flush();
                            byteArrayOutputStream.close();
                            byteArrayOutputStreamMap.put(messageId,byteArrayOutputStream);
                        }
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < by.length; i++)
                        {
                            sb.append("{"+by[i]+"},");
                        }
                        System.out.println(sb);
                        System.out.println("ssss"+Arrays.toString(byteArrayOutputStreamMap.get(messageId).toByteArray()));
                        System.out.println(byteArrayOutputStreamMap.get(messageId).toString(StandardCharsets.UTF_8));

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String message){
        byte messageId = getMessageId();
        byte[] sb = message.getBytes(); // 转化为字节数组
        ArrayList<byte[]> newByteArr = MessageUtil.reviseArr(sb, messageId);
        int i = 0;
        BufferedOutputStream ps = null;
        System.out.println(newByteArr.size());
        for (byte[] bytes : newByteArr) {
            System.out.println(i++);
            System.out.println(Arrays.toString(bytes));
            try {
                ps = new BufferedOutputStream(socket.getOutputStream());
                ps.write(bytes);   // 写入输出流，将内容发送给客户端的输入流
                ps.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public byte getMessageId(){
        if (messageId == maxMessageId){
            messageId =  minMessageId;
        }
        return messageId++;
    }
}
