package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.service.IDistrictService;
import com.kgc.house.service.ITypeService;
import com.kgc.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("typeController1")
@RequestMapping("/admin/")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @RequestMapping("getType")
    @ResponseBody
    public Map<String,Object> getType(Page page){
        PageInfo<Type> pageInfo = typeService.getAllType(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //实现添加
    @RequestMapping("addType")
    @ResponseBody
    public String addType(Type type){
        int temp=-1;
        //调用业务
        try{
           temp= typeService.addType(type);

        }catch (Exception ex){
            ex.printStackTrace();//都会选择日志
        }
        return "{\"result\":"+temp+"}";
    }

    //通过主键查询单条
    @RequestMapping("getSingleType")
    @ResponseBody
    public Type getSingleType(Integer id){
    return typeService.getType(id);
    }

    //实现修改
    @RequestMapping("updType")
    @ResponseBody
    public String updType(Type type){
        int temp=-1;
        //调用业务
        try{
            temp= typeService.updateType(type);

        }catch (Exception ex){
            ex.printStackTrace();//都会选择日志
        }
        return "{\"result\":"+temp+"}";
    }
    //实现删除
    @RequestMapping("delType")
    @ResponseBody
    public String delType(Integer id){

        int temp = typeService.deleteType(id);


        return "{\"result\":"+temp+"}";
    }


    //实现批量删除
    @RequestMapping("delMoreType")
    @ResponseBody
    public String delMoreType(String id){
     String[]arys=id.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<arys.length;i++){
            list.add(Integer.parseInt(arys[i]));
        }
          int temp= typeService.delMoreType(list);
        return "{\"result\":"+temp+"}";
    }

}
