package com.kgc.house.mapper;

import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseExample;
import com.kgc.house.util.HouseCondition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询用户所有的出租房信息
    //实体的作用：传递数据
    public List<House>getHouseByUserId(Integer uid);


    //查询出租房信息
    public House getHouse(String id);

    //查询用户未审核的出租房信息
    public List<House>getHouseByIsPass(Integer ispass);

   //查询用户浏览的出租房信息
    public List<House> getBorswerHouse(HouseCondition houseCondition);




}