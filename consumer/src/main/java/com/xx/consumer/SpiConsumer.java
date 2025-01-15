package com.xx.consumer;

import com.xx.corerpc.RpcApplication;
import com.xx.corerpc.config.RpcConfig;
import com.xx.corerpc.utils.ConfigUtils;
import com.xx.corerpc.proxy.ServiceProxyFactory;
import com.xx.model.User;
import com.xx.service.UserService;

/**
 * 测试Spi机制
 */
public class SpiConsumer {
    public static void main(String[] args){

        // 自己写的配置文件只要以rpc开头，就可以使用默认初始化
        RpcConfig rpc = RpcApplication.getRpcConfig();
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user1 = new User();
        user1.setName("第一次调用！");
        userService.getUser(user1);

        System.err.println("====================分割线========================");

        User user2 = new User();
        user2.setName("第二次调用！");
        userService.getUser(user2);

    }
}
