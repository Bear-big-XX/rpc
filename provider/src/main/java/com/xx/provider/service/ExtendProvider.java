package com.xx.provider.service;


import com.xx.service.UserService;
import com.xx.corerpc.RpcApplication;
import com.xx.corerpc.registry.LocalRegistry;
import com.xx.corerpc.server.HttpServer;
import com.xx.corerpc.server.VertxHttpServer;

/**
 * 扩展服务提供者示例
 *
 */
public class ExtendProvider {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.startServer(RpcApplication.getRpcConfig().getServerPort());
    }
}
