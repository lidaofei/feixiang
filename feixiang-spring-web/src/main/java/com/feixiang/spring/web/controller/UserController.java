package com.feixiang.spring.web.controller;

import com.feixiang.spring.web.common.Result;
import com.feixiang.spring.web.model.User;
import com.feixiang.spring.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lidaofei
 * @Date: 2019/7/11 15:43
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/test")
    public byte[] detail(@RequestBody User param){
        System.out.println("==param="+param.toString());
        if(param.getId() == null){
            return null;
        } else if(param.getId()==1){
            return new byte[]{};
        }
        return param.toString().getBytes();
    }

    @ResponseBody
    @RequestMapping("/detail")
    public Result detail(HttpServletRequest request, HttpServletResponse response){
        System.out.println("1111");
        Result result = new Result();
        User user = userService.get(1L);
        result.setData(user);
        return result;
    }

    @ResponseBody
    @RequestMapping("/detailTwo")
    public Object detailTwo(HttpServletRequest request, HttpServletResponse response){
        System.out.println("1111");
        return "hello";
    }
}
