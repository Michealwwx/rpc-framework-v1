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

    private AtomicInteger count = new AtomicInteger();

    public ServerPublisher(int port, Object service) {
        this.port = port;
        this.service = service;
    }

    public void publish() {
        ServerSocket serverSocket = null;
        while (true) {
            try {
                serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                es.submit(new ProcessorHandler(socket, service));
                System.out.println("客户端总数："+count.addAndGet(1));
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
            }

        }
    }
}
