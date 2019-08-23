package com.kgc.house.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.IDistrictService;
import com.kgc.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private StreetMapper streetMapper;


    @Override
    public PageInfo<District> getAllDistrict(Page page) {
        //封装条件实体类
        PageHelper.startPage(page.getPage(),page.getRows());
        DistrictExample districtExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);
        PageInfo<District> districtPageInfo = new PageInfo<District>(list);
        return  districtPageInfo;
    }

    @Override
    public int addDistrict(District district) {

        return districtMapper.insertSelective(district);
    }


    /**
     * 查询单条信息
     * @param id
     * @return
     */
    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id) ;
    }


    /**
     * 修改的业务
     * @param district
     * @return
     */
    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }


    //删除业务
    @Override
    @Transactional
    //表示该方法基于事务执行
    public int deleteDistrict(Integer id){
        //先删除外键，在删除主键
        //删除区域的同时还要删除街道
        //删除街道
        streetMapper.deleteStreetByDid(id);
        //删除区域
        districtMapper.deleteByPrimaryKey(id);
        return 1;

    }

    //多项删除
    @Override
    public int delMoreDistrict(List<Integer> ids) {
        return districtMapper.deleteMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        DistrictExample districtExample = new DistrictExample();
        return districtMapper.selectByExample(districtExample);
    }


}

