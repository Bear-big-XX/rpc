package com.xx.corerpc.server.tcp;


import com.xx.corerpc.model.RpcRequest;
import com.xx.corerpc.model.RpcResponse;
import com.xx.corerpc.protocol.ProtocolMessage;
import com.xx.corerpc.protocol.ProtocolMessageDecoder;
import com.xx.corerpc.protocol.ProtocolMessageEncoder;
import com.xx.corerpc.protocol.ProtocolMessageTypeEnum;
import com.xx.corerpc.registry.LocalRegistry;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.Method;

public class TcpServerHandler implements Handler<NetSocket> {

    @Override
    public void handle(NetSocket socket) {
        TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
            // 处理请求代码
        });
        socket.handler(bufferHandlerWrapper);
    }
}
