package com.example.ipay.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ipay.bean.SysRoleMenuMapping;
import com.example.ipay.mapper.SysRoleMenuMappingMapper;
import com.example.ipay.service.SysRoleMenuMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleMenuMappingServiceImpl extends ServiceImpl<SysRoleMenuMappingMapper, SysRoleMenuMapping> implements SysRoleMenuMappingService {
    @Autowired
    SysRoleMenuMappingMapper sysRoleMenuMappingMapper;

    @Override
    public List<SysRoleMenuMapping> getMenuListByRoleId(String id) {

        List<SysRoleMenuMapping> sysRoleMenuMappings = sysRoleMenuMappingMapper.getLoginUserMenu(id);

        return sysRoleMenuMappings;
    }
}
