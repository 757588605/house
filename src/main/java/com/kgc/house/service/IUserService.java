package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.util.Page;
import com.kgc.house.util.UserCondition;

public interface IUserService {
    //查询所有管理员用户
    PageInfo<Users> getUserByPage(UserCondition condition);

    //一个功能一个方法:检查用户名是否存在
        int  checkUserName(String name);

        //注册用户
    int addUser(Users users);

    //实现登入  通过用户名密码查询数据库
         Users  login(String username,String password);

}
