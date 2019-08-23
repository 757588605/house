package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.IStreetService;
import com.kgc.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StreetServiceImpl implements IStreetService {

    @Autowired
    private StreetMapper streetMapper;


    @Override
    public PageInfo<Street> getStreetByDid(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        List<Street> list = streetMapper.selectByExample(streetExample);
        PageInfo<Street> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Street> getAllStreet(Integer id) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(id);

        return streetMapper.selectByExample(streetExample) ;
    }
}
