package com.example.ipay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.SysMenu;


public interface SysMenuService extends IService<SysMenu> {

    boolean  insertList(SysMenu sysMenu);

    boolean delectById(String id);

    boolean updateById(SysMenu sysMenu);


}
