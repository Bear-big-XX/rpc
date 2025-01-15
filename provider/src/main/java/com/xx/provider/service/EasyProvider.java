package com.xx.provider.service;

import com.xx.easyrpc.registry.LocalRegistry;
import com.xx.easyrpc.server.MyHttpServer;
import com.xx.easyrpc.server.VertxHttpServer;
import com.xx.service.UserService;

/**
 * 简易服务提供者示例
 */
public class EasyProvider {

    public static void main(String[] args){
        // 注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        // 提供web服务
        MyHttpServer httpServer = new VertxHttpServer();
        httpServer.startServer(8080);
    }
}
