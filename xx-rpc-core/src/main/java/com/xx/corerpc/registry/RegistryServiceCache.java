package com.xx.corerpc.registry;

import com.xx.corerpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册中心服务本地缓存
 */
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     *
     * @param newServiceCache
     * @return
     */
    void writeCache(List<ServiceMetaInfo> newServiceCache) {
        System.err.println("写服务缓存！！！");
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     *
     * @return
     */
    List<ServiceMetaInfo> readCache() {
        System.err.println("读服务缓存！！！");
        return this.serviceCache;
    }

    /**
     * 清空缓存
     */
    void clearCache() {
        System.err.println("清空服务缓存！！！");
        this.serviceCache = null;
    }
}
