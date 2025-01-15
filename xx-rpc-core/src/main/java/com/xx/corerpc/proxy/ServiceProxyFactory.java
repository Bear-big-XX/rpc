package com.xx.corerpc.proxy;


import com.xx.corerpc.RpcApplication;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂（用于创建代理对象）
 */
public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass) {

        if(RpcApplication.getRpcConfig().isMock()){
            return getMockProxy(serviceClass);
        }
//        ClassLoader：用于定义代理类的类加载器。
//        Class[]：一个类数组，指定代理对象要实现的接口。
//        InvocationHandler：一个调用处理器，用于处理对代理对象方法的调用。

      // 使用http
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxyUseHttp());

        // 使用tcp
//        return (T) Proxy.newProxyInstance(
//                serviceClass.getClassLoader(),
//                new Class[]{serviceClass},
//                new ServiceProxyUseTcp());
    }


    /**
     * 根据服务类获取Mock代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getMockProxy(Class<T> serviceClass){
        return (T)Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy()
        );
    }
}

