package com.example.ipay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.SysRoleMenuMapping;

import java.util.List;


public interface SysRoleMenuMappingService extends IService<SysRoleMenuMapping> {


    List<SysRoleMenuMapping> getMenuListByRoleId(String id);
}
