package com.example.ipay.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ipay.bean.SysUsers;
import com.example.ipay.mapper.SysUsersMapper;
import com.example.ipay.service.SysUsersService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements SysUsersService {

    @Autowired
    SysUsersMapper sysUsersMapper;

    @Override
    public boolean insertUser(SysUsers sysUsers) {

        sysUsers.setPassWord(DigestUtils.md5Hex(sysUsers.getPassWord()));

        sysUsers.setId(UUID.randomUUID().toString());

        sysUsersMapper.insert(sysUsers);

        return true;
    }

    @Override
    public String login(String userName, String password) {
        String md5Password = DigestUtils.md5Hex(password);
        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName",userName).eq("password",md5Password);
        SysUsers bean = sysUsersMapper.selectOne(queryWrapper);
        if(bean!=null){
            return bean.getRoleId();
        }
        return null;
    }

    @Override
    public boolean updatePassword(String newPassword,String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        SysUsers bean = sysUsersMapper.selectOne(queryWrapper);
        String md5NewPassword = DigestUtils.md5Hex(newPassword);
        bean.setPassWord(md5NewPassword);
        Integer result = sysUsersMapper.update(bean,queryWrapper);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delectUserById(String id) {

        sysUsersMapper.deleteById(id);

        return true;
    }

    @Override
    public boolean isUpdatePassword(String id, String oldPassword) {
        String md5Password = DigestUtils.md5Hex(oldPassword);
        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id).eq("password",md5Password);
        Integer result = sysUsersMapper.selectCount(queryWrapper);
        if(result>0){
            return true;
        }
        return false;
    }
}
