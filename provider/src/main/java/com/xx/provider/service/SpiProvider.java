package com.xx.provider.service;

import com.xx.corerpc.registry.LocalRegistry;
import com.xx.corerpc.server.MyHttpServer;
import com.xx.corerpc.server.VertxHttpServer;
import com.xx.service.UserService;

/**
 * SPI服务提供者示例
 */
public class SpiProvider {

    public static void main(String[] args){
        // 注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        // 提供web服务
        MyHttpServer httpServer = new VertxHttpServer();
        httpServer.startServer(8080);
    }
}
