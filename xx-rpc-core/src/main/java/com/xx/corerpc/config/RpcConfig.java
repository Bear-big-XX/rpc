package com.xx.corerpc.config;


import com.xx.corerpc.serializer.SerializerKeys;
import lombok.Data;
import lombok.Lombok;

/**
 * RPC 框架配置
 */
@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "xx-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

    /**
     * 模拟调用
     */
//    如果字段名以 "is" 开头,Lombok 会直接使用字段名作为 getter 方法名
//    如果字段名不以 "is" 开头,
//    Lombok 会自动生成 "is" + 首字母大写的字段名作为 getter 方法名
    private boolean mock = false;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();


}
