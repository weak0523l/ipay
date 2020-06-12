package com.example.ipay.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ipay.bean.SysUsers;
import com.example.ipay.mapper.SysUsersMapper;
import com.example.ipay.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements SysUsersService {
    @Autowired
    SysUsersMapper sysUsersMapper;
    @Override
    public String login(String userName ,String password) {
        SysUsers bean = sysUsersMapper.login(userName,password);
        if(bean!=null){
            return bean.getRoleId();
        }
        return null;
    }

    @Override
    public SysUsers findUserById(String id) {
        SysUsers bean = sysUsersMapper.selectById(Integer.parseInt(id));
        return bean;
    }

    @Override
    public SysUsers findUserByName(String userName) {
        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<SysUsers>();
        queryWrapper.eq("UserName",userName);
        SysUsers bean = sysUsersMapper.selectOne(queryWrapper);
        return bean;
    }

    @Override
    public Integer register(SysUsers sysUsers) {
        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<SysUsers>();
        queryWrapper.eq("UserName",sysUsers.getUserName());
        Integer result;
        if(sysUsersMapper.selectCount(queryWrapper)>0){
            return 0;
        }
        result= sysUsersMapper.insert(sysUsers);
        return result;
    }

    @Override
    public boolean update(SysUsers sysUsers) {

         sysUsersMapper.updateById(sysUsers);
         return true;
    }

    @Override
    public boolean delete(String id) {
        sysUsersMapper.deleteById(Integer.parseInt(id));
        return true;
    }

    @Override
    public List<SysUsers> list(String pagenow, String pagecount) {
        Page<SysUsers> page = new Page<>(Integer.parseInt(pagenow.trim()),Integer.parseInt(pagecount.trim()));
        IPage<SysUsers> iPage = sysUsersMapper.selectPage(page, null);
        List<SysUsers> list = iPage.getRecords();
        return list;
    }


}
