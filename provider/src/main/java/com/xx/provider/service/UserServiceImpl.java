package com.xx.provider.service;


import com.xx.model.User;
import com.xx.service.UserService;

/**
 * 用户服务实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名： " + user.getName());
        return user;
    }
}
