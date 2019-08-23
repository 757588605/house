package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersExample;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.service.IUserService;
import com.kgc.house.util.MD5Utils;
import com.kgc.house.util.Page;
import com.kgc.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUserByPage(UserCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        UsersExample usersExample = new UsersExample();
        //添加条件
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(1);//管理员

        //添加搜索条件
        if (condition.getName()!=null){
            criteria.andNameLike("%"+condition.getName()+"%");
        }
        if (condition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }


        List<Users> list = usersMapper.selectByExample(usersExample);
        PageInfo<Users> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int checkUserName(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(0);//表示注册普通用户
        List<Users> list = usersMapper.selectByExample(usersExample);
        return list.size();//0没有 1存在
    }

    @Override
    public int addUser(Users users) {
        //设置为注册用户  可以选择在数据库中给此字段给默认0
        users.setIsadmin(0);
        //在数据库中保存密码时，不要用明文
        //给用户注册的密码进行MD5加密：确保数据的安全性
        String password = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(password);
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users login(String username, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //传用户名 传密码
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        //执行
        List<Users> list = usersMapper.selectByExample(usersExample);
            if (list.size()==0){
                return null;
            }else {
                return list.get(0);
            }

    }
}
