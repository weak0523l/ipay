package com.example.ipay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.SysUsers;


public interface SysUsersService extends IService<SysUsers> {

    SysUsers login(SysUsers sysUsers);
    SysUsers findUserById (String id);
    SysUsers findUserByName (String userName);
    Integer register(SysUsers sysUsers);
    void update(SysUsers sysUsers);
    void delete(String id);
}
