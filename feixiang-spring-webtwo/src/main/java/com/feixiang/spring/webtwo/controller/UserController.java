package com.feixiang.spring.webtwo.controller;

import com.feixiang.spring.webtwo.common.Result;
import com.feixiang.spring.webtwo.model.User;
import com.feixiang.spring.webtwo.service.UserService;
import org.springframework.stereotype.Controller;

/**
 * @Author: lidaofei
 * @Date: 2019/7/11 15:43
 * @Description:
 */
@Controller
public class UserController {

    private UserService userService;

    public Result detail(){
        Result result = new Result();
        User user = userService.get(1L);
        result.setData(user);
        return result;
    }
}
