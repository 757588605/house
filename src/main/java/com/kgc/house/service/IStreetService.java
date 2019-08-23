package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.util.Page;

import java.util.List;

public interface IStreetService {
    //查询某区域下的街道
    PageInfo<Street> getStreetByDid(Integer id, Page page);

    //查询某区域下的所有街道
    List<Street>getAllStreet(Integer id);

}
