package com.example.ipay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.SysUsers;


public interface SysUsersService extends IService<SysUsers> {


    boolean insertUser(SysUsers sysUsers);
    String login(String userName, String password);

    boolean updatePassword(String newPassword,String userName);
    boolean delectUserById(String id);
    boolean isUpdatePassword(String userName,String oldPassword);
}
