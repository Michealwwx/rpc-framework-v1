package com.reign.common;

import java.io.Serializable;

/**
 * @ClassName RpcRequest
 * @Description 请求包装类
 * @Author wuwenxiang
 * @Date 2019-09-17 20:56
 * @Version 1.0
 **/
public class RpcRequest implements Serializable {

    private String className;

    private String methodName;

    private Object[] params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public RpcRequest() {
    }

    public RpcRequest(String className, String methodName, Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.params = params;
    }
}
