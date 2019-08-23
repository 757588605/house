package com.kgc.house.controllers;


import com.kgc.house.entity.Street;
import com.kgc.house.service.IStreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("streetController2")
@RequestMapping("/page/")
public class StreetController {
    @Autowired
    private IStreetService streetService;

    /*显示发布出租房的页面*/
    @RequestMapping("getStreetByDid")
    @ResponseBody
    public List<Street> getStreetByDid(Integer id){
        //查询街道
      List<Street> streets= streetService.getAllStreet(id);
      return streets;
    }

}
