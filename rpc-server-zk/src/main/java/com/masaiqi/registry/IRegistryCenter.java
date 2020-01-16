package com.masaiqi.registry;

/**
 * 注册中心接口
 *
 * @author sq.ma
 * @date 2020/1/15 下午7:04
 */
public interface IRegistryCenter {

    void registry(String serviceName, String serviceAddress);
}
