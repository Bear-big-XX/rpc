package com.xx.consumer;

import com.xx.corerpc.RpcApplication;
import com.xx.corerpc.config.RpcConfig;
import com.xx.corerpc.utils.ConfigUtils;

/**
 * 扩展服务消费者示例
 */
public class ConsumerGlobalSetting {


    public static void main(String[] args) {
        // 测试动态加载配置，该模块的application.properties文件会覆盖corerpc模块中的application.properties文件
        //RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "yy");
        RpcConfig rpc = RpcApplication.getRpcConfig();
        System.out.println(rpc);
    }
}
