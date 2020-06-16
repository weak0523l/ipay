package com.example.ipay.conf;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ipay.bean.SysUsers;
import com.example.ipay.mapper.SysUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    SysUsersMapper sysUsersMapper;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserName",username);
        SysUsers userinfo = sysUsersMapper.selectOne(queryWrapper);
        if(userinfo==null){
            throw  new UsernameNotFoundException("用户不存在！");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(userinfo.getUserName(),passwordEncoder.encode(userinfo.getPassWord()),authorities);
        return userDetails;
    }

}
