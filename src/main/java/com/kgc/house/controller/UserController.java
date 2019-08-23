package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.Users;
import com.kgc.house.service.IUserService;
import com.kgc.house.util.Page;
import com.kgc.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("userController1")
@RequestMapping("/admin/")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("getUser")
    @ResponseBody
    public Map<String,Object> getUser(UserCondition condition){

        PageInfo<Users> pageInfo = userService.getUserByPage(condition);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
