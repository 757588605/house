package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import com.kgc.house.mapper.TypeMapper;
import com.kgc.house.service.ITypeService;
import com.kgc.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeServiceImpl implements ITypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public PageInfo<Type> getAllType(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        //查询所有
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        PageInfo<Type> PageInfo = new PageInfo<>(list);
        return PageInfo;

    }

    @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public int deleteType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreType(List<Integer> ids) {
        return typeMapper.deleteMoreType(ids);
    }

    @Override
    public List<Type> getAllType() {
        TypeExample typeExample = new TypeExample();

        return typeMapper.selectByExample(typeExample) ;
    }
}
