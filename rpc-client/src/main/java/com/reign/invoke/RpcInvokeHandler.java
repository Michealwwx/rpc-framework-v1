package com.reign.invoke;

import com.reign.common.RpcRequest;
import com.reign.net.RpcNetTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName RpcInvokeHandler
 * @Description 动态代理实际处理类；
 * @Author wuwenxiang
 * @Date 2019-09-17 22:43
 * @Version 1.0
 **/
public class RpcInvokeHandler implements InvocationHandler {


    private String host;

    private int port;

    public RpcInvokeHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 实际上上层调用的服务方法通过动态代理最后调用的就是这个方法；
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("come in");
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        //发送请求得到返回；
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        Object object = rpcNetTransport.sendRequest(rpcRequest);

        return object;
    }

    private void remoteCall() {

    }

}
