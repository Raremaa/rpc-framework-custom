package com.masaiqi.discovery;

import java.util.List;

/**
 * 负载均衡接口
 *
 * @author sq.ma
 * @date 2020/1/16 下午1:53
 */
public interface LoadBalanceStrategy {

    String selectHost(List<String> servicePathList);
}
