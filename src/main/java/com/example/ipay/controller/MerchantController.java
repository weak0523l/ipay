package com.example.ipay.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ipay.bean.Echarts;
import com.example.ipay.bean.PayMerchant;
import com.example.ipay.bean.PayOrders;
import com.example.ipay.service.PayMerchantService;
import com.example.ipay.service.PayOrdersService;
import com.example.ipay.util.FileUpload;
import com.example.ipay.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin
@Api(tags = "测试商户管理")
public class MerchantController {
    @Autowired
    PayMerchantService payMerchantService;
    @Autowired
    PayOrdersService payOrdersService;
    @PostMapping("/insertList")
    @ResponseBody
    @ApiOperation("新增商户信息")
    @ApiImplicitParam(name = "PayMerchant" ,value = "商户信息对象",required = true,dataType = "com.yishang.pay.merchantutil.bean.PayMerchant")
    public R insertList(@RequestBody PayMerchant payMerchant){
        //参数校验
        if(
                payMerchant.getId()!=null&&
                payMerchant.getAppkey()!=null&&
                payMerchant.getMerchname()!=null&&
                payMerchant.getSwiffpassmerchno()!=null&&
                payMerchant.getSwiffpasspaysecert()!=null&&
                payMerchant.getOpenchannel()!=null&&
                payMerchant.getCreatetime()!=null&&
                payMerchant.getCreateuser()!=null&&
                StringUtils.isNotBlank(payMerchant.getCertpath())
        ){
            if(payMerchantService.insertList(payMerchant)){
                return R.ok("增加成功");
        }
            return R.error(400,"添加失败,数据不能为空");
        }

       return R.error(400,"输入的值不符合规则,请重新输入");
    }
    @PostMapping("/delectById")
    @ResponseBody
    @ApiOperation(value = "根据商户ID删除商户信息")
    @ApiImplicitParam(name = "id" ,value = "商户ID",required = true,dataType = "String")
    public R delectById(@RequestBody String id){
        //参数校验
        if(StringUtils.isNotBlank(id)){
            if(payMerchantService.delectById(id)){
                return R.ok("删除成功！！");
            }
            return R.error(400,"删除失败");
        }

       return R.error(400,"请输入正确的ID");
    }
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation("修改商户信息")
    @ApiImplicitParam(name = "PayMerchant" ,value = "商户对象",required = true,dataType = "com.yishang.pay.merchantutil.bean.PayMerchant")
    public R update(@RequestBody PayMerchant payMerchant){
        //参数校验
        if(payMerchantService.updateMerById(payMerchant)){
            return R.ok("修改成功！");
        }
        return R.error(400,"修改失败");

    }
    @PostMapping(value = "/getPage")
    @ResponseBody
    @ApiOperation(value = "分页获取商户",notes = "获取用户列表")
    @ApiImplicitParam(name = "params" ,value = "表单查询对象（pagenow-当前页和pagenum-每页显示条数必传）",required = true,dataType = "Map<String,Object>",paramType = "body")
    public R getPage(@RequestBody Map<String,Object> params){
        //参数校验
        Object pagenow = params.get("pagenow");

        Object pagenum = params.get("pagenum");
        if(pagenow==null ||StringUtils.isBlank(pagenow+"")){

            return R.error(400,"pagenow不能为空");

        }if (pagenum==null ||StringUtils.isBlank(pagenum+"")){

            return R.error(400,"pagenum不能为空");
        }
        Page<PayMerchant> page = new Page<>(Integer.parseInt(pagenow.toString()),Integer.parseInt(pagenum.toString()));

        QueryWrapper<PayMerchant> wrapper = new QueryWrapper<>();

        if(payMerchantService.page(page,wrapper).getRecords().size()!=0){

            return R.ok(payMerchantService.page(page,wrapper));
        }
        return R.error(400,"查询失败");
    }


  /*  @PostMapping(value = "/getPageOrder")
    @ResponseBody
    @ApiOperation(value = "根据Appkey查询商户对应订单信息并进行分页")
    public List<PayOrders> getPageOrder( String pagenow, String pagecount, String Appkey){
        List<PayOrders> payOrders =  payOrdersService.getPageOrder(pagenow,pagecount,Appkey);
        return payOrders;
    }*/
    @PostMapping(value = "/getPageOrder")
    @ResponseBody
    @ApiOperation(value = "根据Appkey查询商户对应订单信息并进行分页")
    @ApiImplicitParam(name = "params" , value = "表单查询对象（pagenow-当前页和pagenum-每页显示条数必传）",required = true,dataType = "Map<String,Object>",paramType = "body")
    public R getPageOrder(@RequestBody Map<String,Object> params){
        //参数校验
        Object pagenow = params.get("pagenow");

        Object pagenum = params.get("pagenum");
        if(pagenow==null ||StringUtils.isBlank(pagenow+"")){

            return R.error(400,"pagenow不能为空");

        }if (pagenum==null ||StringUtils.isBlank(pagenum+"")){

            return R.error(400,"pagenum不能为空");
        }
        Page<PayOrders> page = new Page<>(Integer.parseInt(params.get("pagenow").toString()),Integer.parseInt(params.get("pagenum").toString()));

        QueryWrapper<PayOrders> wrapper = new QueryWrapper<>();

        wrapper.eq("AppKey",params.get("appkey").toString());

        return R.ok(payOrdersService.page(page,wrapper).getRecords());
    }

    @PostMapping("/upload")
    @ResponseBody
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(name = "file",value = "文件上传",required = true,dataType = "MultipartFile")
    public R uploadFile(@RequestBody MultipartFile file){
        //new一个上传文件工具类
        FileUpload fileUpload = new FileUpload();

        //将工具类中返回的文件地址接受并返回给前端
        String filePath = fileUpload.uploadFile(file);

        return R.ok(filePath);
    }


    @PostMapping(value = "/sumMoney")
    @ResponseBody
    @ApiOperation(value = "根据Appkey查询商户的总收入")
    @ApiImplicitParam(name = "appKey" ,value = "所属商户",required = true,dataType = "String")
    public R sumMoney(@RequestParam String appKey){
        //参数校验
        if(appKey!=null){
            String sunMoney = payMerchantService.sumMoey(appKey);

            if(sunMoney==null){
                return R.error(400,"未查询到商户收入");
            }
            return R.ok(sunMoney);
        }

        return R.error(400,"未获取到商户信息");
    }

    @PostMapping(value = "/everydayMoney")
    @ResponseBody
    @ApiOperation(value = "根据Appkey查询商户的每天收入")
    @ApiImplicitParam(name = "appKey" ,value = "所属商户",required = true,dataType = "String")
    public R everydayMoney(@RequestParam String appKey){
        //参数校验
        if(appKey!=null){
            List<Map> list = payMerchantService.everydayMoney(appKey);
            if(list==null){
                return R.error(400,"未查询到商户收入");
            }
            return R.ok(list);
        }

        return R.error(400,"未获取到商户信息");
    }

    @ApiOperation(value = "获取商铺每月收入数据")
    @PostMapping("/echarsShow")
    @ResponseBody
/*    @ApiImplicitParam(name = "params" , value = "传入appKey及月份",required = true,dataType = "Map<String,Object>",paramType = "map")*/
    public List<Echarts> echartsShow(@RequestParam Map<String,Object> params) {
        Object appKey = params.get("appKey");
        Object month = params.get("month");
        List<Echarts> list = new ArrayList<Echarts>();
        List<Map> list2 = payMerchantService.everydayMoney(appKey.toString());
        for(Map list1 : list2){

            if(list1.get("月").toString().equals(month.toString())){
                list.add(new Echarts(list1.get("日").toString() + "日", Float.valueOf(list1.get("收入合计").toString())));
            }

        }
        return list;
    }

}
