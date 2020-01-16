package com.masaiqi;

import com.masaiqi.discovery.IServiceDiscovery;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author sq.ma
 * @date 2020/1/15 下午3:20
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private IServiceDiscovery serviceDiscovery;

    private String version;

    private RemoteInvocationHandler() {

    }

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery, String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //请求数据的包装
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        rpcRequest.setVersion(version);

        String serviceName = rpcRequest.getClassName();
        if(!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }

        String servicePath = serviceDiscovery.discovery(serviceName);

        //远程通信
        RpcNetTransport netTransport=new RpcNetTransport(servicePath);
        Object result=netTransport.send(rpcRequest);
        return result;
    }
}
