package com.xx.provider.service;


import com.xx.service.UserService;
import com.xx.corerpc.RpcApplication;
import com.xx.corerpc.config.RegistryConfig;
import com.xx.corerpc.config.RpcConfig;
import com.xx.corerpc.model.ServiceMetaInfo;
import com.xx.corerpc.registry.LocalRegistry;
import com.xx.corerpc.registry.Registry;
import com.xx.corerpc.registry.RegistryFactory;
import com.xx.corerpc.server.tcp.VertxTcpServer;

/**
 * 服务提供者示例
 *
 */
public class TcpProvider{

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
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

        // 启动 TCP 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.startServer(8080);
    }
}

