package com.dddqmmx.surf.server.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MessageUtil {
    public static int maxBytes = 1024;
    public static ArrayList<byte[]> reviseArr(byte[] by, byte messageId) {
        ArrayList<byte[]> result = new ArrayList<>();
        int cycles = by.length/maxBytes;
        int lastArrayLength = by.length % maxBytes;

        if (lastArrayLength != 0){
            cycles+=1;
        }
        int sb = 0;
        int byteIndex = 0;
        for (int i = 0;i < cycles; i++) {
            byte[] bytes = null;
            if (i == cycles-1) {
                bytes = new byte[lastArrayLength + 3];
            } else {
                bytes = new byte[maxBytes + 3];
            }
            int nssnsns = 0;
            for (int j = 0; j < bytes.length-3; j++)
            {
                bytes[j+3] = by[byteIndex];
                if (bytes[j+3] != by[byteIndex]){
                    System.out.println(j+"出问题了");
                }
                byteIndex++;
            }
            System.out.println("sb"+sb);
            bytes[0] = messageId;
            bytes[1] = 1;
            System.out.println("byteIndex"+i);

            if (cycles-1 == i){
                bytes[2] = 1;
            }
            result.add(bytes);
        }
        return result;
    }

}
