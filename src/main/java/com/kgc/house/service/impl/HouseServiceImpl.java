package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseExample;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.service.IHouseService;
import com.kgc.house.util.HouseCondition;
import com.kgc.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseServiceImpl implements IHouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUser(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        //查询所有记录
        List<House> list = houseMapper.getHouseByUserId(id);
        PageInfo<House> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public House getHouse(String id) {



        return houseMapper.getHouse(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delHouse(String id, Integer isdel) {
        //创建出租房实体
        House house = new House();
        house.setId(id);
        house.setIsdel(isdel);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByIsPass(Integer ispass,Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        //查询所有
        List<House> list = houseMapper.getHouseByIsPass(ispass);
        return new PageInfo<>(list);
    }

    @Override
    public int houseIsPass(String id, Integer ispass) {
        House house = new House();
        house.setId(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getBorswerHouse(HouseCondition houseCondition) {
        PageHelper.startPage(houseCondition.getPage(),houseCondition.getRows());
        List<House> list = houseMapper.getBorswerHouse(houseCondition);
        PageInfo<House> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
