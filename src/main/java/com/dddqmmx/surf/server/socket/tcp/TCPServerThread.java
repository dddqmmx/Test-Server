package com.dddqmmx.surf.server.socket.tcp;

import com.dddqmmx.surf.server.util.MessageUtil;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
    private BufferedOutputStream bos = null;

    public TCPServerThread(Socket socket,String sessionId) {
        this.socket = socket;
        this.sessionId = sessionId;
    }

    @Override
    public void run() {
        try {
            bos = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String message){
        byte[] sb = message.getBytes(); // 转化为字节数组
        ArrayList<byte[]> newByteArr = MessageUtil.reviseArr(sb, getMessageId());
/*        StringBuffer stringBuffer = new StringBuffer();;
        for (int i = 0; i < newByteArr.length; i++)
        {
            stringBuffer.append("{"+newByteArr[i]+"},");
        }
        System.out.println(stringBuffer);
        try {
            bos.write(newByteArr);  // 把内容发给服务器
            bos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }

    public byte getMessageId(){
        if (messageId == maxMessageId){
            messageId =  minMessageId;
        }
        return messageId++;
    }
}
