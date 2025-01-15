package com.xx.provider.service;


import com.xx.service.UserService;
import com.xx.corerpc.RpcApplication;
import com.xx.corerpc.registry.LocalRegistry;
import com.xx.corerpc.server.MyHttpServer;
import com.xx.corerpc.server.VertxHttpServer;

/**
 * 扩展服务提供者示例
 *
 */
public class ProviderGlobalSetting {

    public static void main(String[] args) {
        // RPC 框架初始化,默认配置
         RpcApplication.init();

        // 加载自定义配置信息
//        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, "yy");
//        RpcApplication.init(rpcConfig);

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        MyHttpServer httpServer = new VertxHttpServer();
        httpServer.startServer(RpcApplication.getRpcConfig().getServerPort());
    }
}
