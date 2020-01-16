package com.masaiqi;

import com.masaiqi.discovery.IServiceDiscovery;
import com.masaiqi.discovery.ServiceDiscoveryWithZK;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcProxyClient = (RpcProxyClient) applicationContext.getBean("rpcPRoxyClient");
        IServiceDiscovery serviceDiscovery = new ServiceDiscoveryWithZK();

        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class, serviceDiscovery, "1.0");
        String result = helloService.sayHello("hello rarema!");
        System.out.println(result);
    }
}
