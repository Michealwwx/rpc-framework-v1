package com.reign.handler;

import com.reign.common.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            //1.反序列化获取请求包装类
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //2.反射调用具体方法
            Object result = invoke(rpcRequest);
            //3.将结果通过socket输出;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
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
            //1.确定参数类型
            Object[] params = rpcRequest.getParams();
            Class<?>[] types = new Class<?>[params.length];
            for (int i = 0; i < types.length; i++) {
                types[i] = params[i].getClass();
            }
            //2.根据方法及参数列表获取方法
            Method method = clazz.getMethod(rpcRequest.getMethodName(), types);
            //3.调用方法
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
