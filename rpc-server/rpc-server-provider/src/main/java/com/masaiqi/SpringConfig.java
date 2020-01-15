package com.masaiqi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sq.ma
 * @date 2020/1/15 下午2:42
 */
@Configuration
@ComponentScan(basePackages = "com.masaiqi")
public class SpringConfig {

    @Bean(name = "RpcServer")
    public RpcServer rpcServer() {
        return new RpcServer(6276);
    }
}
