package com.dddqmmx.surf.server.console;


import com.dddqmmx.surf.server.socket.ConnectList;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * debug console
 */
public class Console extends Thread{
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String sessionId = null;
            String massage = null;
            String command = scanner.nextLine();
            switch (command){
                case "send":
                    System.out.println("输入发送内容");
                    massage = scanner.nextLine();
                    System.out.println("输入发送人的sessionId");
                    sessionId = scanner.nextLine();
                    ConnectList.connectList.get(sessionId).getTcpServerThread().send(massage);
                case "userSessionList":
                    break;
                default:
                    System.out.println("未知指令");
            }
        }
    }
}
