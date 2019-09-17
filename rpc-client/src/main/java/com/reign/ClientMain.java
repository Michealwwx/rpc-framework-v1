package com.reign;

import com.reign.interfaces.UserService;
import com.reign.model.User;
import com.reign.proxy.RpcClientProxy;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

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
        //userService是一个代理类
        UserService userService = rpcClientProxy.clientProxy(UserService.class,host,port);
        //sayHello实际调用的是动态代理RpcInvokeHandler中的invoke方法；
        String result = userService.sayHello("");
        System.out.printf("返回结果："+result);
        //User user = userService.addUserAge(new User("",3));

    }

}
