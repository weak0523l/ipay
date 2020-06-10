package com.example.ipay.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ipay.bean.SysUsers;
import com.example.ipay.mapper.SysUsersMapper;
import com.example.ipay.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements SysUsersService {
    @Autowired
    SysUsersMapper sysUsersMapper;
    @Override
    public SysUsers login(SysUsers sysUsers) {
        SysUsers bean = sysUsersMapper.login(sysUsers);
        return bean;
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
        return null;
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
    public void update(SysUsers sysUsers) {
        sysUsersMapper.updateById(sysUsers);
    }

    @Override
    public void delete(String id) {
        sysUsersMapper.deleteById(Integer.parseInt(id));
    }

}