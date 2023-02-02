package com.dddqmmx.surf.server.util;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil {
    public static int maxBytes = 1024;
    public static ArrayList<byte[]> reviseArr(byte[] by, int messageId) {
        ArrayList<byte[]> result = new ArrayList<>();
        int cycles = by.length/maxBytes;
        int lastArrayLength = by.length % maxBytes;
        if (lastArrayLength != 0){
            cycles+=1;
        }
        int byteIndex = 0;
        for (int i = 0;i < cycles; i++){
            byte[] bytes = null;
            if (i == cycles){
                bytes = new byte[lastArrayLength];
            }else {
                bytes = new byte[maxBytes];
            }
            for (int j = 0; j < maxBytes; j++) {
                if (byteIndex == by.length){
                    break;
                }
                bytes[i] = by[byteIndex];
                byteIndex++;
            }
            byte[] newByteArr = new byte[maxBytes + 2];
            StringBuffer sb = new StringBuffer();
            for (int k = 0; k < lastArrayLength; k++)
            {
                newByteArr[k+2] = by[k];
                sb.append("{").append(by[k]).append("},");
            }
            System.out.println(sb);
            result.add(newByteArr);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(reviseArr("我是大傻逼".getBytes(),1).size());
    }
}
