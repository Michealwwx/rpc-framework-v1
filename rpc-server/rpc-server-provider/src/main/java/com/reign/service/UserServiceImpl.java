package com.reign.service;

import com.reign.interfaces.UserService;
import com.reign.model.User;

/**
 * @ClassName UserServiceImpl
 * @Description 实现类
 * @Author wuwenxiang
 * @Date 2019-09-17 21:13
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "Hello ,My friend " + name;
    }

    @Override
    public User addUserAge(User user) {
        if (user != null) {
            user.setAge(user.getAge() + 1);
        }
        return user;
    }
}
