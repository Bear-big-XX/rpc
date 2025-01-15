package com.xx.corerpc.server;


import io.vertx.core.Vertx;

public class VertxHttpServer implements MyHttpServer {

    @Override
    public void startServer(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("服务器启动成功！正在监听端口 " + port);
            } else {
                System.err.println("服务器启动失败: " + result.cause() + port);
            }
        });
    }
}
