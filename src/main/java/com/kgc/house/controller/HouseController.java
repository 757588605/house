package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.service.IHouseService;
import com.kgc.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "houseController1")
@RequestMapping("/admin/")
public class HouseController {
    //以下所有方法全部返回异步
    @Autowired
    private IHouseService houseService;

    //查询未审核的功能
    @RequestMapping("getHouseNoPass")
    public Map<String,Object>getHouseNoPass(Page page){
        //调用业务
        PageInfo<House> pageInfo = houseService.getHouseByIsPass(0, page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //查询已审核的功能
    @RequestMapping("getHouseYesPass")
    public Map<String,Object>getHouseYesPass(Page page){
        //调用业务
        PageInfo<House> pageInfo = houseService.getHouseByIsPass(1, page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    //通过审核
    @RequestMapping("houseYes")
    public String houseYes(String id){
        //调用业务
        int temp = houseService.houseIsPass(id,1);
        return "{\"result\":"+temp+"}";
    }

    @RequestMapping("houseNo")
    public String houseNo(String id){
        //调用业务
        int temp = houseService.houseIsPass(id,0);
        return "{\"result\":"+temp+"}";
    }
}
