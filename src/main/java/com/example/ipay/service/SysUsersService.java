package com.example.ipay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.SysUsers;


public interface SysUsersService extends IService<SysUsers> {


    boolean insertUser(SysUsers sysUsers);
    SysUsers login(String userName, String password);

    boolean updatePassword(String newPassword,String id);
    boolean delectUserById(String id);
    boolean isUpdatePassword(String id,String oldPassword);
}
