package com.masaiqi.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 服务注册中心-zk实现
 *
 * @author sq.ma
 * @date 2020/1/15 下午7:05
 */
public class RegistryCenterWithZK implements IRegistryCenter {

    CuratorFramework curatorFramework = null;

    {
        //新建zk连接,设置重试策略,设置会话超时5s,设置连接字符串,设置命名空间
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("ma_registry")
                .build();
        curatorFramework.start();
    }

    @Override
    public void registry(String serviceName, String serviceAddress) {
        String servicePath = "/" + serviceName;
        //利用zk持久节点和临时节点特性,可以实现服务名称持久化,服务对应的url地址随着服务载入而创建,服务宕机而自动删除
        try {
            if(curatorFramework.checkExists().forPath(servicePath) == null ) {
                curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath);
                System.out.println("服务(zk节点) " + servicePath + "注册成功");
            }
            String addressPath = servicePath + "/" + serviceAddress;
            curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath);
            System.out.println("服务(zk节点) " + addressPath + "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
