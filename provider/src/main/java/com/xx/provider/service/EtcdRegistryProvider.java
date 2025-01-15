package com.xx.provider.service;


import com.xx.service.UserService;
import com.xx.corerpc.RpcApplication;
import com.xx.corerpc.config.RegistryConfig;
import com.xx.corerpc.config.RpcConfig;
import com.xx.corerpc.model.ServiceMetaInfo;
import com.xx.corerpc.registry.LocalRegistry;
import com.xx.corerpc.registry.Registry;
import com.xx.corerpc.registry.RegistryFactory;
import com.xx.corerpc.server.MyHttpServer;
import com.xx.corerpc.server.VertxHttpServer;

/**
 * etcd服务提供者示例
 *
 */
public class EtcdRegistryProvider {

    public static void main(String[] args) {
        // RPC 框架初始化
        System.err.println("RPC框架开始初始化！！！");
        RpcApplication.init();
        System.err.println("RPC框架初始化成功！！！");

        String serviceName = UserService.class.getName();
        System.err.println("服务名是：" + serviceName);

        // 注册服务到本地
        System.err.println(serviceName + "注册到本地！");
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        MyHttpServer httpServer = new VertxHttpServer();
        httpServer.startServer(RpcApplication.getRpcConfig().getServerPort());
    }
}
