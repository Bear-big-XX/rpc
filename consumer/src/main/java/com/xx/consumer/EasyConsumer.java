package com.xx.consumer;


import com.xx.easyrpc.proxy.ServiceProxyFactory;
import com.xx.model.User;
import com.xx.service.UserService;

/**
 * 服务消费者示例
 */
public class EasyConsumer {

    public static void main(String[] args){
//        // 获取UserService的实现类对象
//        UserService userService = null;
//        User user = new User();
//        user.setName("xx");
//
//        // 调用
//        User newUser = userService.getUser(user);
//        if(newUser != null){
//            System.out.println("成功调用！" + newUser.getName());
//        }else{
//            System.out.println("调用失败！");
//        }


        // 静态代理
        UserService userService1 = new UserServiceStaticProxy();
        User user1 = new User();
        user1.setName("静态代理！");
        userService1.getUser(user1);

        // 动态代理
        UserService userService2 = ServiceProxyFactory.getProxy(UserService.class);
        User user2 = new User();
        user2.setName("动态代理");
        userService2.getUser(user2);


        /*
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("动态代理！");

        // 调用
        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println(newUser.getName());
        }else{
            System.out.println("user == null");
        }
        int number = userService.getNumber();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(number);
*/

    }
}
