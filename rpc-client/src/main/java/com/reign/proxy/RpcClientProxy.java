package com.reign.proxy;

import com.reign.invoke.RpcInvokeHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName RpcClientProxy
 * @Description 用户代理类，主要为了封装底层通信细节；
 * @Author wuwenxiang
 * @Date 2019-09-17 21:16
 * @Version 1.0
 **/
public class RpcClientProxy {

    /**
     * 动态代理具体的接口
     *
     * @param interfaceClass 代理的接口
     * @param host       server的ip
     * @param port       server的port
     * @param <T>
     * @return
     */
    public <T> T clientProxy(final Class<T> interfaceClass, final String host, final int port) {
        //这里用的是jdk的动态代理，后期考虑使用cglib
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},new RpcInvokeHandler(host,port));
    }

}
