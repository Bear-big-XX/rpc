package com.xx.corerpc;


import com.xx.corerpc.config.RegistryConfig;
import com.xx.corerpc.config.RpcConfig;
import com.xx.corerpc.constant.RpcConstant;
import com.xx.corerpc.registry.Registry;
import com.xx.corerpc.registry.RegistryFactory;
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

        System.err.println("传入的rpc配置信息：" + rpcConfig.toString());

        // 注册中心初始化
        System.err.println("registryConfig配置信息初始化！！！");
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();

        System.err.println("通过RegistryConfig类的register字段来决定要实例化哪个注册中心类！！！读取到的注册类型为：" + registryConfig.getRegistry());
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());

        System.err.println("调用registry.init(registryConfig)实例化注册中心！！！");
        registry.init(registryConfig);


        // 创建并注册 Shutdown Hook，JVM 退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            System.err.println("调用ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX)加载RpcConfig配置文件！！！");
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
            System.err.println("成功读取RpcConfig配置文件！！！");
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        System.err.println("使用传入的Rpc配置文件进行初始化！！！RpcConfig: " + newRpcConfig.toString());
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
