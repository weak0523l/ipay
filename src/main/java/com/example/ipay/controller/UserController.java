package com.example.ipay.controller;


import com.example.ipay.bean.SysUsers;
import com.example.ipay.conf.JwtUtil;
import com.example.ipay.service.SysUsersService;
import com.example.ipay.token.CheckToken;
import com.example.ipay.token.LoginToken;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("SysUsers")
public class UserController {


    @Autowired
    SysUsersService sysUsersService;


    @PostMapping("login")
    @ResponseBody
    @LoginToken
    @ApiOperation("用户登录")
    public Object  login(@RequestBody SysUsers sysUsers){
        JSONObject jsonObject = new JSONObject();
        SysUsers bean = sysUsersService.login(sysUsers);
        if(bean==null){
            return "FAIL";
        }
        String token = JwtUtil.createJWT(6000000, bean);
        jsonObject.put("token", token);
        jsonObject.put("user", bean);
        return jsonObject;
    }

    @CheckToken
    @GetMapping("/getMessage")
    @ResponseBody
    public String getMessage() {
        return "你已通过验证";
    }


    @PostMapping("register")
    @ResponseBody
    @LoginToken
    @ApiOperation("用户注册")
    public String  register(@RequestBody SysUsers sysUsers){
        Integer isSuccees = sysUsersService.register(sysUsers);
        if (isSuccees == 0) {
            return "账号已存在";
        }else {
            return "注册成功";
        }
    }

    @PostMapping("update")
    @ResponseBody
    @LoginToken
    @ApiOperation("用户修改信息")
    public String  update(@RequestBody SysUsers sysUsers){
        sysUsersService.update(sysUsers);
        return  "修改成功";
    }

    @PostMapping("delete")
    @ResponseBody
    @LoginToken
    @ApiOperation("删除用户")
    public String  delete(String id){
        sysUsersService.delete(id);
        return  "删除成功";
    }
}
