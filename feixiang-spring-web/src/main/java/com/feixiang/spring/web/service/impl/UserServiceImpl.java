package com.feixiang.spring.web.service.impl;

import com.feixiang.spring.web.model.User;
import com.feixiang.spring.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: lidaofei
 * @Date: 2019/7/11 15:44
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User get(Long id) {
        System.out.println("222");
        User user = new User();
        user.setId(1L);
        user.setName("李道飞");
        user.setAge(1);
        return user;
    }
}
