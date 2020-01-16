package com.masaiqi.discovery;

import java.util.List;
import java.util.Random;

/**
 * 随机负载均衡
 *
 * @author sq.ma
 * @date 2020/1/16 下午1:53
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    protected String doSelect(List<String> servicePathList) {
        int length = servicePathList.size();
        Random random = new Random();
        return servicePathList.get(random.nextInt(length));
    }
}
