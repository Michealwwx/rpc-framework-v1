package com.reign;

import com.reign.interfaces.UserService;
import com.reign.server.ServerPublisher;
import com.reign.service.UserServiceImpl;

/**
 * @ClassName ServerMain
 * @Description main
 * @Author wuwenxiang
 * @Date 2019-09-17 20:52
 * @Version 1.0
 **/
public class ServerMain {

    private static int serverPort = 8080;

    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        ServerPublisher serverPublisher = new ServerPublisher(serverPort, userService);
        serverPublisher.publish();
    }

}
