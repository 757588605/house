package com.kgc.house.controllers;


import com.kgc.house.entity.Type;
import com.kgc.house.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "typeController2")
@RequestMapping("/page/")
public class TypeController {
@Autowired
private ITypeService typeService;

@RequestMapping("getType")
    @ResponseBody
    public List<Type> getType(){
        return typeService.getAllType();
}



}
