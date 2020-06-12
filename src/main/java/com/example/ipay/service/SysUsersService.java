package com.example.ipay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.SysUsers;

import java.util.List;


public interface SysUsersService extends IService<SysUsers> {

    String login(String userName ,String password);
    SysUsers findUserById (String id);
    SysUsers findUserByName (String userName);
    Integer register(SysUsers sysUsers);
    boolean update(SysUsers sysUsers);
    boolean delete(String id);
    List<SysUsers> list(String pagenow, String pagecount);

}
