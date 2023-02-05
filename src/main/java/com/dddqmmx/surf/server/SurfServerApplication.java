package com.dddqmmx.surf.server;

import com.dddqmmx.surf.server.console.Console;
import com.dddqmmx.surf.server.socket.tcp.TCPServer;
import com.dddqmmx.surf.server.socket.udp.UDPServer;
import com.dddqmmx.surf.server.util.MessageUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class SurfServerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SurfServerApplication.class, args);
/*        TCPServer tcpServer = new TCPServer();
        tcpServer.start();
        Console console = new Console();
        console.start();*/
        byte[] bytes = "例えば君の顔に昔よりシワが増えても それでもいいんだ 僕がギターを思うように弾けなくなっても 心の歌は君で溢れているよ 高い声も出せずに思い通り歌えない それでもうなずきながら一緒に歌ってくれるかな 割れんばかりの拍手も 響き渡る歓声もいらない 君だけ 分かってよ 分かってよ Darlin' 夢が叶ったの お似合いの言葉が見つからないよ Darlin' 夢が叶ったの 「愛してる」 たった一度の たった一人の 生まれてきた幸せ味わってるんだよ 今日がメインディッシュで 終わりの日には甘酸っぱいデザートを食べるの 山も谷も全部フルコースで 気が利くような言葉はいらない 素晴らしい特別もいらない ただずっと ずっと側に置いていてよ 僕の想いは歳をとると増えてくばっかだ 好きだよ 分かってよ 分かってよ ねえ、Darlin' 夢が叶ったの お似合いの言葉が見つからないよ Darlin' 夢が叶ったの 愛が溢れていく 君が僕を忘れてしまっても ちょっと辛いけど… それでもいいから 僕より先に どこか遠くに 旅立つことは 絶対 許さないから 生まれ変わったとしても 出会い方が最悪でも また僕は君に恋するんだよ 僕の心は君にいつも片想い 好きだよ 分かってよ 分かってよ 分かってよ Darlin' 夢が叶ったの お似合いの言葉が見つからないよ Darlin' 夢が叶ったの ねえ Darlin' 「愛してる」".getBytes(StandardCharsets.UTF_8);
        //byte[] bytes = "测试一下测试一下测试一下测试一下测试一下测试一下测试一下        测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下".getBytes(StandardCharsets.UTF_8);
        System.out.print(Arrays.toString(bytes));
        System.out.println("bytesLength : "+bytes.length);
        ArrayList<byte[]> bytes1 = MessageUtil.reviseArr(bytes, (byte) 1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int newLength = 0;
        for (byte[] bytes2 : bytes1) {
            System.out.print(Arrays.toString(bytes2));
            System.out.println();
            newLength += bytes2.length;
            System.out.println(bytes2.length);
            byteArrayOutputStream.write(bytes2,2,bytes2.length-2);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        }
        System.out.println("newLength : "+newLength);
        //String s = new String(bytes, StandardCharsets.UTF_8);
        String s = Arrays.toString(byteArrayOutputStream.toByteArray());
        System.out.println(s);
        System.out.println(byteArrayOutputStream.toString());
    }

}
