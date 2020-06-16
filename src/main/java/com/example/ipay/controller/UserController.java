package com.example.ipay.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ipay.bean.SysRoleMenuMapping;
import com.example.ipay.bean.SysUsers;
import com.example.ipay.service.SysRoleMenuMappingService;
import com.example.ipay.service.SysUsersService;
import com.example.ipay.util.JwtTokenUtils;
import com.example.ipay.util.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    SysUsersService sysUsersService;
    @Autowired
    SysRoleMenuMappingService sysRoleMenuMappingService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "params" ,value = "包含账号密码",required = true,dataType = "Map<String,Object>")
    public R  login(@RequestBody Map<String,Object> params){
        Object username = params.get("username");
        Object password = params.get("password");
        if(username==null || password==null){
            return R.error(400,"有未填写字段");
        }
        String roleId = sysUsersService.login(username.toString(),password.toString());
        Map<String,Object> map = new HashMap<>();
        if(roleId!=null){
            String token = JwtTokenUtils.createToken(username.toString(),true);
            map.put("token",token);
            map.put("roleId",roleId);
            return R.ok(map);
        }
        return R.error(400,"登陆失败");
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    @ApiOperation("用户修改密码")
    @ApiImplicitParam(name = "params" ,value = "包含一次旧密码，一次新密码",required = true,dataType = "Map<String,Object>")
    public R  updatePassword(@RequestBody Map<String,Object> params){
        Object oldPassword = params.get("oldPassword");
        Object newPassword1 = params.get("newPassword1");
        Object newPassword2 = params.get("newPassword2");
        if(oldPassword==null||newPassword1==null||newPassword2==null){
            return R.error(400,"有未输入字段");
        }
        if(sysUsersService.isUpdatePassword("admin",oldPassword.toString())==false){
            return R.error(400,"旧密码错误");
        }
        if(newPassword1.equals(newPassword2)==false){
            return R.error(400,"新密码不一致");
        }

        boolean result = sysUsersService.updatePassword(newPassword1.toString(),"admin");
        if(result){
            return R.ok("修改成功");
        }
        return R.error(400,"修改失败");
    }

    @PostMapping(value = "/list")
    @ResponseBody
    @ApiOperation("用户分页")
    @ApiImplicitParam(name = "params", value = "表单查询对象（pagenow-当前页和pagenum每页条数必传）", required = true, dataType = "Map<String,Object>", paramType = "body")
    public R list(@RequestBody Map<String, Object> params) {
        //参数校验
        Object pagenow = params.get("pagenow");

        Object pagenum = params.get("pagenum");
        //参数校验
        if (pagenow == null || StringUtils.isBlank(pagenow + "")) {

            return R.error(400, "pagenow不能为空");

        }
        if (pagenum == null || StringUtils.isBlank(pagenum + "")) {

            return R.error(400, "pagenum不能为空");
        }

        Page<SysUsers> page = new Page<>(Integer.parseInt(pagenow.toString()), Integer.parseInt(pagenum.toString()));

        QueryWrapper<SysUsers> wrapper = new QueryWrapper<>();

        if (sysUsersService.page(page, wrapper).getRecords().size() != 0) {

            return R.ok(sysUsersService.page(page, wrapper).getRecords());
        }
        return R.error(400, "查询失败");

    }

    @GetMapping(value = "/getMenuListByRoleId")
    @ResponseBody
    @ApiOperation("查询角色所对应的菜单功能")
    public R getMenuListByRoleId(String id) {
        //参数校验
        if (id != null) {
            List<SysRoleMenuMapping> sysMenus = sysRoleMenuMappingService.getMenuListByRoleId(id);

            return R.ok(sysMenus);
        }
        return R.error(400, "没接收到id");
    }

    @PostMapping(value = "insertUser")
    @ResponseBody
    @ApiOperation("新增用户信息")
    @ApiImplicitParam(name = "SysUsers" ,value = "用户信息对象",required = true,dataType = "com.yishang.pay.merchantutil.bean.SysUsers")
    public R insertUser(@RequestBody SysUsers sysUsers) {
        //参数校验
        if (
                StringUtils.isNotBlank(sysUsers.getId()) &&
                StringUtils.isNotBlank(sysUsers.getUserName()) &&
                StringUtils.isNotBlank(sysUsers.getPassWord()) &&
                StringUtils.isNotBlank(sysUsers.getRoleId()) &&
                sysUsers.getCreateTime() != null
        ) {
            if (sysUsersService.insertUser(sysUsers)) {
                return R.ok("添加成功");
            }
            return R.error(400, "添加失败,数据不能为空");
        }
        return R.error(400,"输入的值不符合规则,请重新输入");
    }

    @PostMapping(value = "/delectUserById")
    @ResponseBody
    @ApiOperation(value = "根据用户ID删除商户信息")
    @ApiImplicitParam(name = "id" ,value = "用户ID",required = true,dataType = "String")
    public R delectUserById(@RequestBody String id){
        //参数校验
        if(StringUtils.isNotBlank(id)){
            if(sysUsersService.delectUserById(id)){
                return R.ok("删除成功！！");
            }
            return R.error(400,"删除失败");
        }

        return R.error(400,"请输入正确的ID");
    }
}
