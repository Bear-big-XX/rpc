package com.xx.corerpc;


import com.xx.corerpc.config.RpcConfig;
import com.xx.corerpc.constant.RpcConstant;
import com.xx.corerpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * RPC 框架应用
 * 相当于 holder，存放了项目全局用到的变量。双检锁单例模式实现
 */
@Slf4j
public class RpcApplication {

    // 使用 volatile 关键字是为了确保变量的可见性和禁止指令重排序。即，当一个线程修改 rpcConfig 时，其他线程能立刻看到最新值，避免因为指令重排导致的单例失效问题。
    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc 初始化成功, config = {}", newRpcConfig.toString());
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置(双检锁)
     *
     * @return
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                    System.out.println("rpcConfig的配置是：" + rpcConfig);
                }
            }
        }
        return rpcConfig;
    }
}
