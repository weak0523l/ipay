package com.example.ipay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ipay.bean.SysUsers;
import org.apache.ibatis.annotations.Select;


public interface SysUsersMapper extends BaseMapper<SysUsers> {
    @Select("SELECT " +
            " su.UserName," +
            " su.PassWord, " +
            " sr.RoleName" +
            " FROM " +
            " Sys_Users su " +
            " INNER JOIN Sys_Roles sr ON su.RoleId = sr.ID  " +
            "WHERE " +
            " su.UserName = #{UserName}  " +
            " AND su.PassWord = #{PassWord} ")
    SysUsers login(SysUsers sysUsers);
}
