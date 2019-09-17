package com.reign.net;

import com.reign.common.RpcRequest;

/**
 * @ClassName RpcNetTransport
 * @Description 封装网络层；
 * @Author wuwenxiang
 * @Date 2019-09-17 23:13
 * @Version 1.0
 **/
public class RpcNetTransport {

    private String host;

    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object sendRequest(RpcRequest request) {
        Object result = null;
        
        return null;
    }

}
