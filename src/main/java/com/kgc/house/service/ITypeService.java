package com.kgc.house.service;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.util.Page;

import java.util.List;

public interface ITypeService {

    /**
     * 查询所有区域
     * @return 区域实体集合
     */
    PageInfo<Type> getAllType(Page page);


    /**
     *  添加业务
     * @param
     * @return
     */
    public int addType(Type type);

    //查询单条记录
    public Type getType(Integer id);

    //修改的业务
    public  int  updateType(Type type);

    //删除业务
    public int deleteType(Integer id);

    //实现批量删除的业务
    public  int delMoreType(List<Integer> ids);

    //查询所有类型
    public List<Type>getAllType();


}
