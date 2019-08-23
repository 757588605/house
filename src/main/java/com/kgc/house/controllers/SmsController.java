package com.kgc.house.controllers;


import com.kgc.house.sms.SmsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/page/")
public class SmsController {


   //接收手机号，发送消息(发的内容含有验证码的消息)
    @RequestMapping("getCode")
    public String  getCode(String sendPhone, HttpSession session){
        //1.生成验证码:生成 五位
        String code=(int)(Math.random()*100000)+"";
        //2.发送短信
        String sendMsg="验证码是:"+code+",请在120秒内输入验证码";
        int result=SmsUtil.sendMsg(sendPhone,sendMsg);

        //3.使用session保存生成的验证码
        session.setAttribute("saveCode",code);
        session.setMaxInactiveInterval(120);  //120秒

        return "{\"result\":"+result +"}";
    }
}
