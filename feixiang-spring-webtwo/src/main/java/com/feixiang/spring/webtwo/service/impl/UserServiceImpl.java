package com.feixiang.spring.webtwo.service.impl;

import com.feixiang.spring.webtwo.model.User;
import com.feixiang.spring.webtwo.service.UserService;

/**
 * @Author: lidaofei
 * @Date: 2019/7/11 15:44
 * @Description:
 */
public class UserServiceImpl implements UserService {

    public User get(Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("ldf");
        user.setAge(1);
        return user;
    }
}
