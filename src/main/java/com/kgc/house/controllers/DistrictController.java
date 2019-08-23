package com.kgc.house.controllers;


import com.kgc.house.entity.District;
import com.kgc.house.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("districtcontroller2")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;

    @RequestMapping("getDistrict")
    @ResponseBody
    public List<District>getDistrict(){
        return districtService.getAllDistrict();
    }


}
