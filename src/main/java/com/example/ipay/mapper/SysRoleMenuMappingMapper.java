package com.example.ipay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ipay.bean.SysRoleMenuMapping;

import java.util.List;


public interface SysRoleMenuMappingMapper extends BaseMapper<SysRoleMenuMapping> {


/*    @Select("SELECT\n" +
            "\tsrm.RoleId roleId,\n" +
            "\tsrm.MenuId menuId,\n" +
            "\tsm.Name name,\n" +
            "\tsm.Title title,\n" +
            "\tsm.Icon icon,\n" +
            "\tsm.Route route,\n" +
            "\tsm.Component component,\n" +
            "\tsm.Sort sort,\n" +
            "\tsm.CreateTime createTime,\n" +
            "\tsm.CreateUser createUser\n" +
            "FROM\n" +
            "\tSys_RoleMenuMapping srm\n" +
            "\tINNER JOIN Sys_Menu sm ON sm.ParentId = srm.MenuId\n" +
            "\tINNER JOIN Sys_Roles sr ON sr.ID = srm.RoleId\n" +
            "\tWHERE sr.ID =#{id}")*/

    List<SysRoleMenuMapping> getLoginUserMenu(String id);
}
