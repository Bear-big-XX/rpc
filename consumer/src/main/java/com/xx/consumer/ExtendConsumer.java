package com.xx.consumer;

import com.xx.corerpc.config.RpcConfig;
import com.xx.corerpc.utils.ConfigUtils;

/**
 * 扩展服务消费者示例
 */
public class ExtendConsumer {


    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "xx");
        System.out.println(rpc);
    }
}
