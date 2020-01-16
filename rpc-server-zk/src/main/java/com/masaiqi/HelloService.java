package com.masaiqi;

import org.springframework.stereotype.Component;

/**
 * @author sq.ma
 * @date 2020/1/15 下午3:43
 */
@RpcService(value = IHelloService.class, version = "1.0")
@Component
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String content) {
        return "你好啊~我收到了内容 : " + content;
    }
}
