package com.masaiqi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcProxyClient = (RpcProxyClient) applicationContext.getBean("rpcPRoxyClient");

        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 6276);
        String result = helloService.sayHello("hello rarema!");
        System.out.println(result);
    }
}
