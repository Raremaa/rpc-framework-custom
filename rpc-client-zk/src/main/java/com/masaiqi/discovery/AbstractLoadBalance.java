package com.masaiqi.discovery;

import java.util.List;

/**
 * @author sq.ma
 * @date 2020/1/16 下午1:55
 */
public abstract class AbstractLoadBalance implements LoadBalanceStrategy {

    @Override
    public String selectHost(List<String> servicePathList) {
        if (servicePathList == null || servicePathList.size() == 0) {
            return null;
        }
        if (servicePathList.size() == 1) {
            return servicePathList.get(0);
        }
        return doSelect(servicePathList);
    }


    protected abstract String doSelect(List<String> servicePathList);
}
