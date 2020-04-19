package com.feixiang.tdd.user.service;

import com.feixiang.tdd.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidaofei
 * @date 2019/12/23 23:56
 */
public class UserServiceImpl {

    public User getInfoById(Long id){
        return new User(1L,"lidaofei",1);
    }

    public List<User> getList(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L,"lidaofei1",1));
        userList.add(new User(2L,"lidaofei2",2));
        userList.add(new User(3L,"lidaofei3",3));
        return userList;
    }
}
