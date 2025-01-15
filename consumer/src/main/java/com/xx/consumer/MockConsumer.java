package com.xx.consumer;

import com.xx.corerpc.proxy.ServiceProxyFactory;
import com.xx.service.UserService;

public class MockConsumer {

    /**
     * 测试Mock代理类
     * @param args
     */
    public static void main(String[] args){
        UserService mockUserService = ServiceProxyFactory.getProxy(UserService.class);


        // 调用UserService接口的默认实现方法
        int result = mockUserService.getNumber();
        System.err.println(result);

    }
}
