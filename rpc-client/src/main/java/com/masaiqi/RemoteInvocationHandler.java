package com.masaiqi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author sq.ma
 * @date 2020/1/15 下午3:20
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    private RemoteInvocationHandler() {
    }

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //请求数据的包装
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        rpcRequest.setVersion("1.0");

        //远程通信
        RpcNetTransport netTransport=new RpcNetTransport(host,port);
        Object result=netTransport.send(rpcRequest);
        return result;
    }
}
