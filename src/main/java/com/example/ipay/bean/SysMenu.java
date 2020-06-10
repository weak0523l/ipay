package com.example.ipay.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("菜单")
public class SysMenu {
    @TableField("ID")
    private String id;
    @TableField("Name")
    private String Name;
    @TableField("Title")
    private String Title;
    @TableField("locn")
    private String Locn;
    @TableField("Route")
    private String Route;
    @TableField("Component")
    private String Component;
    @TableField("Sort")
    private Integer Sort;
    @TableField("ParentId")
    private String ParentId;
    @TableField("CreateTime")
    private Date CreateTime;
    @TableField("CreateUser")
    private String CreateUser;
}
