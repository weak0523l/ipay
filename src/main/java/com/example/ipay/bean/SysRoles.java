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
@ApiModel("角色表")
public class SysRoles {
    @TableField("ID")
    private String id;
    @TableField("RoleName")
    private String RoleName;
    @TableField("Remarks")
    private String Remarks;
    @TableField("CreateTime")
    private Date CreateTime;
}
