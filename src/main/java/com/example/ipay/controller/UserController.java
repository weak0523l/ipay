package com.example.ipay.controller;


import com.example.ipay.bean.SysUsers;
import com.example.ipay.interceptor.CheckToken;
import com.example.ipay.interceptor.LoginToken;
import com.example.ipay.service.SysUsersService;
import com.example.ipay.util.JwtUtil;
import com.example.ipay.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Api(value="用户controller",tags={"用户操作接口"})
@RequestMapping("SysUsers")
public class UserController {


    @Autowired
    SysUsersService sysUsersService;


    @PostMapping("/login")
    @ResponseBody
    @LoginToken
    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "SysUsers" ,value = "用户信息对象",required = true,dataType = "SysUsers")
    public R  login(@RequestBody SysUsers sysUsers){
        SysUsers bean = sysUsersService.login(sysUsers);
        if(bean==null){
            return R.error(400,"登陆失败");
        }
        String token = JwtUtil.createJWT(6000000,bean);
        return R.ok(token);
    }

    @CheckToken
    @GetMapping("/getMessage")
    @ResponseBody
    public String getMessage() {
        return "你已通过验证";
    }


    @PostMapping("/register")
    @ResponseBody
    @ApiOperation("用户注册")
    @ApiImplicitParam(name = "SysUsers" ,value = "用户信息对象",required = true,dataType = "SysUsers")
    public R register(@RequestBody SysUsers sysUsers){
        Integer isSuccees = sysUsersService.register(sysUsers);
        if (isSuccees == 0) {
            return R.error(400,"当前账号已存在");
        }else if (isSuccees>0){
            return R.ok("注册成功");
        }
        return R.error(400,"注册失败");
    }

    @PostMapping("/update")
    @ResponseBody
    @ApiOperation("用户修改信息")
    @ApiImplicitParam(name = "SysUsers" ,value = "用户信息对象",required = true,dataType = "SysUsers")
    public R  update(@RequestBody SysUsers sysUsers){
        if(sysUsersService.update(sysUsers)){
            return R.ok("修改成功");
        }
        return  R.error(400,"修改失败");
    }

    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id" ,value = "用户id",required = true,dataType = "String")
    public R  delete(String id){
        if(sysUsersService.delete(id)){
            return R.ok("删除成功");
        }
        return  R.error(400,"删除失败");
    }

    @PostMapping("/list/{pagenow}/{pagecount}")
    @ResponseBody
    @ApiOperation(value = "查询用户",notes = "传入当前页码和每页数据数目")
    @ApiImplicitParam(paramType = "String")
    public R list(@PathVariable String pagenow,@PathVariable String pagecount){
        if(pagenow.trim().equals("")||pagecount.trim().equals("")){
            return R.error(400,"当前字段有空");
        }
        List<SysUsers> list = sysUsersService.list(pagenow,pagecount);
        if(list==null){
            return R.error(400,"未找到数据");
        }
        return  R.ok(list);

    }
}
