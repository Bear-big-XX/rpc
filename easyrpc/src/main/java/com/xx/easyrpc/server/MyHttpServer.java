package com.xx.easyrpc.server;

/**
 * 自定义Http服务接口
 */
public interface MyHttpServer {

    /**
     * 启动服务器
     * @param port
     */
    void startServer(int port);
}
