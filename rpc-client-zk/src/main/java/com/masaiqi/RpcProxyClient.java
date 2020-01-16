package com.masaiqi;

import com.masaiqi.discovery.IServiceDiscovery;
import com.masaiqi.discovery.ServiceDiscoveryWithZK;

import java.lang.reflect.Proxy;

/**
 * @author sq.ma
 * @date 2020/1/15 下午3:15
 */
public class RpcProxyClient {

    public <T> T clientProxy(Class<T> interfaceCls, IServiceDiscovery serviceDiscovery, String version) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(serviceDiscovery, version));
    }
}
