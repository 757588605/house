package com.kgc.house.service;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.util.Page;

import java.util.List;

public interface IDistrictService {

    /**
     * 查询所有区域
     * @return 区域实体集合
     */
    PageInfo<District> getAllDistrict(Page page);


    /**
     *  添加业务
     * @param
     * @return
     */
    public int addDistrict(District district);

    //查询单条记录
    public District getDistrict(Integer id);

    //修改的业务
    public  int  updateDistrict(District district);

    //删除业务
    public int deleteDistrict(Integer id);

    //实现批量删除的业务
    public  int delMoreDistrict(List<Integer> ids);

    //查询所有的区域
    public List<District>getAllDistrict();


}
