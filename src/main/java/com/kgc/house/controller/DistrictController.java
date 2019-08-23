package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.service.IDistrictService;
import com.kgc.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("districtcontroller1")
@RequestMapping("/admin/")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;

    @RequestMapping("getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrict(Page page){
        PageInfo<District> pageInfo = districtService.getAllDistrict(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //实现添加
    @RequestMapping("addDistrict")
    @ResponseBody
    public String addDistrict(District district){
        int temp=-1;
        //调用业务
        try{
           temp= districtService.addDistrict(district);

        }catch (Exception ex){
            ex.printStackTrace();//都会选择日志
        }
        return "{\"result\":"+temp+"}";
    }

    //通过主键查询单条
    @RequestMapping("getSingleDistrict")
    @ResponseBody
    public District getSingleDistrict(Integer id){
    return districtService.getDistrict(id);
    }

    //实现修改
    @RequestMapping("updDistrict")
    @ResponseBody
    public String updDistrict(District district){
        int temp=-1;
        //调用业务
        try{
            temp= districtService.updateDistrict(district);

        }catch (Exception ex){
            ex.printStackTrace();//都会选择日志
        }
        return "{\"result\":"+temp+"}";
    }
    //实现删除
    @RequestMapping("delDistrict")
    @ResponseBody
    public String delDistrict(Integer id){
        try{
           districtService.deleteDistrict(id);
            return "{\"result\":1}";
        }catch (Exception e){
            System.out.println("出错拉");
        }
        return "{\"result\":0}";
    }


    //实现批量删除
    @RequestMapping("delMoreDistrict")
    @ResponseBody
    public String delMoreDistrict(String id){
     String[]arys=id.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<arys.length;i++){
            list.add(Integer.parseInt(arys[i]));
        }
          int temp= districtService.delMoreDistrict(list);
        return "{\"result\":"+temp+"}";
    }

}
