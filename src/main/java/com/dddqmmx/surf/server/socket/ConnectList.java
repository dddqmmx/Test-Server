package com.dddqmmx.surf.server.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectList {

    //String : session id ; Connect : 对应Connect;
    public static Map<String,Connect> connectList = new HashMap<>();
    //String : session id ; Connect : SocketSession;
    public static Map<String,SocketSession> sessionList = new HashMap<>();
}
