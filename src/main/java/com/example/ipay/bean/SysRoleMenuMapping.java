package com.example.ipay.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("角色菜单列表")
public class SysRoleMenuMapping {
    @TableField("ID")
    private String id;
    @TableField("RoleId")
    private String RoleId;
    @TableField("MenuId")
    private String MenuId;
    @TableField(exist = false)//该表中不存在字段
    private List<SysMenu> menuList;
    @TableField(exist = false)//该表中不存在字段
    private List<SysRoles> rolesList;
}
