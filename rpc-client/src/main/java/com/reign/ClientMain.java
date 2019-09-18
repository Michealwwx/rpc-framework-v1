package com.reign;

import com.reign.interfaces.UserService;
import com.reign.model.User;
import com.reign.proxy.RpcClientProxy;

/**
 * @ClassName ClientMain
 * @Description 主函数
 * @Author wuwenxiang
 * @Date 2019-09-17 20:51
 * @Version 1.0
 **/
public class ClientMain {

    private static String host = "localhost";

    private static int port = 8080;

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        for (int i = 0; i < 1000; i++) {

            //1.获取代理类
            UserService userService = rpcClientProxy.clientProxy(UserService.class, host, port);
            //2.调用方法
            String result = userService.sayHello("wuwux");
            System.out.printf("sayHello result ：" + result);
            User user = userService.addUserAge(new User("", 3));
            System.out.println("addUserAge result ：" + user.toString());
        }

    }
}
