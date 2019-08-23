package com.kgc.house.controllers;


import com.kgc.house.entity.Users;
import com.kgc.house.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("userController2")
@RequestMapping("/page/")
public class UserController {
    @Autowired
    private IUserService userService;

    //检查用户名是否存在
    @RequestMapping("checkUname")
    @ResponseBody
    public  String  checkUname(String name){
        //调用业务
        int temp = userService.checkUserName(name);
        return "{\"result\":"+temp+"}";
    }


    @RequestMapping("reg")
    public String reg(Users users){
        int temp = userService.addUser(users);

        if (temp>0){
            return "login";  //进入登陆页面
        }else {
            return "regs";   //进入注册页面
        }
    }

    @RequestMapping("loginAction")
    public String login(String veryCode,String name , String password, Model model, HttpSession session){
        //比较验证码   veryCode表示输入验证码
        String savecode=(String)session.getAttribute("saveCode");
        if(veryCode.equals(savecode)){
            Users user = userService.login(name, password);
            if (user==null){
                model.addAttribute("info","用户名和密码不正确");
                return "login";
            }else {
                //只要登入，使用session作用域保存登入的人
                session.setAttribute("loginInfo",user);
                //设置保存的有效时间
                session.setMaxInactiveInterval(6000);//以秒
                return "redirect:getHouse"; //管理页
            }
        }else {
            model.addAttribute("info","验证码错误");
            return "login";
        }



    }

}
