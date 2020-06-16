package com.example.ipay.bean.vo;


import lombok.Data;

import java.util.Date;

@Data
public class SysRoleMenuMappingVo {

    private String roleId;
    private String menuId;
    private String component;
    private String route;
    private Integer sort;
    private String createUser;
    private Date createTime;
    private String title;
    private String icon;
    private String name;
}
