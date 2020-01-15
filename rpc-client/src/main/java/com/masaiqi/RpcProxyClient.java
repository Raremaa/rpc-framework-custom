package com.masaiqi;

import java.lang.reflect.Proxy;

/**
 * @author sq.ma
 * @date 2020/1/15 下午3:15
 */
public class RpcProxyClient {

    public <T> T clientProxy(Class<T> interfaceCls, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }
}
