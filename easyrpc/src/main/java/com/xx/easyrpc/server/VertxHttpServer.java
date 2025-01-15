package com.xx.easyrpc.server;


import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class VertxHttpServer implements MyHttpServer {

    @Override
    public void startServer(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.err.println("服务器成功启动！监听端口号： " + port);
            } else {
                System.err.println("服务器启动失败： " + result.cause());
            }
        });
    }
}
