package com.example.ipay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.PayOrders;


import java.util.List;

public interface PayOrdersService extends IService<PayOrders> {

    List<PayOrders> getPageOrder(String pagenow, String pagecount, String appkey);

    List<PayOrders> list(String pagenow, String pagecount);
}
