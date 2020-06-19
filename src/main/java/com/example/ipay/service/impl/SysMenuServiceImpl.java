package com.example.ipay.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ipay.bean.SysMenu;
import com.example.ipay.mapper.SysMenuMapper;
import com.example.ipay.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public boolean insertList(SysMenu sysMenu) {
        Integer result = sysMenuMapper.insert(sysMenu);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delectById(String id) {
        Integer result = sysMenuMapper.deleteById(id);
        System.out.println(id+result);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateById(SysMenu sysMenu) {
        Integer result = sysMenuMapper.updateById(sysMenu);
        if(result>0){
            return true;
        }
        return false;
    }
}
