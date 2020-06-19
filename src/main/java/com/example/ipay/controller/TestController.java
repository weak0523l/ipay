package com.example.ipay.controller;


import com.example.ipay.bean.PayOrders;
import com.example.ipay.conf.ExcelConfig;
import com.example.ipay.service.PayOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.util.List;


@RestController
public class TestController {

    @Autowired
    PayOrdersService payOrdersService;


    @GetMapping("/list/{pagenow}/{pagecount}")
    @ResponseBody
    public List<PayOrders> list(@PathVariable String pagenow, @PathVariable String pagecount) throws Exception{
       List<PayOrders> payOrders = payOrdersService.list(pagenow,pagecount);

        ExcelConfig<PayOrders> util = new ExcelConfig<>();
        String[] columnNames = { "ID", "OrderNo", "Money" ,"AppKey","AuthCode","OperaUser","PayState","PayDate","PayType","PayChannel","CreateTime"};
        util.exportExcel("用户导出", columnNames, payOrders, new FileOutputStream("E:/test.xlsx "), ExcelConfig.EXCEl_FILE_2007);
        return payOrders;
    }

    @GetMapping("/getMessage")
    @ResponseBody
    public String getMessage () {
        return "hello horld";
    }

    @PostMapping("/test")
    public String test(@RequestBody String text){
        return text;
    }

}
