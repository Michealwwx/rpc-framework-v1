package com.reign.handler;

import com.reign.common.RpcRequest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @ClassName ProcessorHandler
 * @Description 请求处理类
 * @Author wuwenxiang
 * @Date 2019-09-17 20:57
 * @Version 1.0
 **/
public class ProcessorHandler implements Runnable {

    private Socket socket;

    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            //反序列化
            objectInputStream = new ObjectInputStream(inputStream);
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //获取要调用的类，方法及参数信息；
            Object result = invoke(rpcRequest);
            //将结果通过socket输出;
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 反射调用具体方法；
     *
     * @param rpcRequest
     * @return
     */
    private Object invoke(RpcRequest rpcRequest) {
        Object result = null;
        try {
            Class clazz = Class.forName(rpcRequest.getClassName());
            //确定参数类型
            Object[] params = rpcRequest.getParams();
            Class<?>[] types = new Class<?>[params.length];
            for (int i = 0; i < types.length; i++) {
                types[i] = params[i].getClass();
            }
            //根据方法及参数列表获取方法
            Method method = clazz.getMethod(rpcRequest.getClassName(), types);

            result = method.invoke(service, params);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
