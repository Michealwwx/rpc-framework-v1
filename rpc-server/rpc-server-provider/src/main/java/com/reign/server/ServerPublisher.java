package com.reign.server;

import com.reign.handler.ProcessorHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ServerPublisher
 * @Description 服务暴露
 * @Author wuwenxiang
 * @Date 2019-09-17 22:24
 * @Version 1.0
 **/
public class ServerPublisher {
    private int port;
    private Object service;
    private static ExecutorService es = Executors.newCachedThreadPool();

    //TODO 统计请求个数;
    private AtomicInteger count = new AtomicInteger();

    public ServerPublisher(int port, Object service) {
        this.port = port;
        this.service = service;
    }

    public void publish() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        while (true) {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("server started !");
                socket = serverSocket.accept();
                count.getAndIncrement();
                System.out.printf("请求个数:{},客户端ip:{},客户端端口:{}",count.get(),socket.getLocalAddress(),socket.getPort());
                es.submit(new ProcessorHandler(socket, service));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
