package com.dddqmx.surf.client;

import com.dddqmmx.surf.server.SurfServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



@SpringBootTest
public class Main {

    public static Map<Byte,ByteArrayOutputStream> byteArrayOutputStreamMap = new HashMap<>();


    public static void main(String[] args) {
        SpringApplication.run(SurfServerApplication.class, args);
        Socket ss = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bosFile = null;    // 与输出文件流相关联
        try {
            ss = new Socket("127.0.0.1", 2042);
            bis = new BufferedInputStream(ss.getInputStream());
            // 等待接收服务器发送回来的消息
            while(true) {
                byte[] by = new byte[1024+3];
                int res = bis.read(by);
                int sendUser = by[0];
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                String format = sdf.format(date);
                if (by[1] == 2) // 说明传的是文件
                {
                    bosFile = new BufferedOutputStream(new FileOutputStream("./directoryTest/用户" + sendUser + "-传输的文件.png", true));
                    bosFile.write(by, 3, res-3);
                    bosFile.flush();
                    if (res<1027)   // 说明是最后一次在传送文件，所以传送的字节数才会小于字节数组by的大小
                    {
                        //System.out.println("客户端接收到的信息" + receive);
                        System.out.println("用户" + sendUser + "\t" + format + ":");
                        System.out.printf("用户%d发送的文件传输完成\n", sendUser);
                    }
                }
                else    // 说明传输的是聊天内容，则按字符串的形式进行解析
                {
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
                    System.out.println(res);
                    /*System.out.println("例えば君の顔に昔よりシワが増えても それでもいいんだ 僕がギターを思うように弾けなくなっても 心の歌は君で溢れているよ 高い声も出せずに思い通り歌えない それでもうなずきながら一緒に歌ってくれるかな 割れんばかりの拍手も 響き渡る歓声もいらない 君だけ 分かってよ 分かってよ Darlin' 夢が叶ったの お似合いの言葉が見つからないよ Darlin' 夢が叶ったの 「愛してる」 たった一度の たった一人の 生まれてきた幸せ味わってるんだよ 今日がメインディッシュで 終わりの日には甘酸っぱいデザートを食べるの 山も谷も全部フルコースで 気が利くような言葉はいらない 素晴らしい特別もいらない ただずっと ずっと側に置いていてよ 僕の想いは歳をとると増えてくばっかだ 好きだよ 分かってよ 分かってよ ねえ、Darlin' 夢が叶ったの お似合いの言葉が見つからないよ Darlin' 夢が叶ったの 愛が溢れていく 君が僕を忘れてしまっても ちょっと辛いけど… それでもいいから 僕より先に どこか遠くに 旅立つことは 絶対 許さないから 生まれ変わったとしても 出会い方が最悪でも また僕は君に恋するんだよ 僕の心は君にいつも片想い 好きだよ 分かってよ 分かってよ 分かってよ Darlin' 夢が叶ったの お似合いの言葉が見つからないよ Darlin' 夢が叶ったの ねえ Darlin' 「愛してる」");
                    System.out.println(byteArrayOutputStreamMap.get(messageId).toString(StandardCharsets.UTF_8));
                    *//*System.out.println(sb);
                    String receive = new String(by, 2, res);
                    System.out.println("用户" + sendUser + "\t" + format + ":");
                    System.out.println(receive);*/
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
