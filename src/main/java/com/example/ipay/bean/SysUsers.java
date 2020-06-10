package com.example.ipay.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("角色表")
public class SysUsers {
    @TableField("ID")
    private String id;
    @TableField("UserName")
    private String UserName;
    @TableField("PassWord")
    private String PassWord;
    @TableField("RoleId")
    private String RoleId;
    @TableField("MerchantId")
    private String MerchantId;
    @TableField("State")
    private Integer State;
    @TableField("CreateTime")
    private Date CreateTime;
    @TableField("CreateUser")
    private String CreateUser;
    @TableField(exist = false)//该表中不存在字段
    private List<SysMenu> sysMenuList;
}
