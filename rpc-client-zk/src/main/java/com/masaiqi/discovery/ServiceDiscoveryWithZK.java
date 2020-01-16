package com.masaiqi.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sq.ma
 * @date 2020/1/16 上午10:26
 */
public class ServiceDiscoveryWithZK implements IServiceDiscovery {

    CuratorFramework curatorFramework = null;

    /**
     * 服务地址本地缓存
     */
    Map<String, List<String>> serviceCache = new ConcurrentHashMap(0);

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
    public String discovery(String serviceName) {
        String path = "/" + serviceName;
        List<String> servicePathList = serviceCache.get(path);
        if (servicePathList == null || servicePathList.size() == 0) {
            try {
                if (curatorFramework.checkExists().forPath(path) == null) {
                    throw new RuntimeException("service not found");
                }
                servicePathList = curatorFramework.getChildren().forPath(path);
                serviceCache.put(serviceName, servicePathList);
                registryWatch(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new RandomLoadBalance().doSelect(servicePathList);
    }

    private void registryWatch(final String path) throws Exception {
        PathChildrenCache nodeCache=new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener nodeCacheListener= (curatorFramework1, pathChildrenCacheEvent) -> {
            System.out.println("客户端收到节点变更的事件");
            serviceCache.put(path, curatorFramework1.getChildren().forPath(path));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();

    }

}
