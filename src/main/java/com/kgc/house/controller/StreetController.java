package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.service.IDistrictService;
import com.kgc.house.service.IStreetService;
import com.kgc.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("streetController1")
@RequestMapping("/admin/")
public class StreetController {
    @Autowired
    private IStreetService streetService;

    @RequestMapping("getStreetByDid")
    @ResponseBody
    public Map<String,Object> getStreetByDid(Integer id, Page page){
        PageInfo<Street>pageInfo = streetService.getStreetByDid(id, page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;


    }
}
