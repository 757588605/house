package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.util.HouseCondition;
import com.kgc.house.util.Page;

import java.util.List;

public interface IHouseService {

    //添加出租房
    public int addHouse(House house);

    //查询用户的出租房信息
    public PageInfo<House> getHouseByUser(Integer id, Page page);

    //查询出租房信息
    public House getHouse(String id);

    //修改出租房信息
    public int updateHouse(House house);




    /**
     *   //删除出租房（逻辑删除）
     * @param id
     * @param isdel 如果传0表示正常，1表示逻辑删除
     * @return
     */
    public  int delHouse(String id,Integer isdel);



    //0表示正常 未审核  1表示已经审核
    //查询未审核的出租房信息
    public PageInfo<House>getHouseByIsPass(Integer ispass,Page page);


    //审核出租房信息
    //0表示未审核 1表示审核
    public int houseIsPass(String id,Integer ispass);


    //查询所有的出租房
    public PageInfo<House>getBorswerHouse(HouseCondition houseCondition);


}
