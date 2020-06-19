package com.example.ipay.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.PayMerchant;

import java.util.List;
import java.util.Map;


public interface PayMerchantService extends IService<PayMerchant> {

    boolean  insertList(PayMerchant payMerchant);

    boolean delectById(String id);

    boolean updateMerById(PayMerchant payMerchant);

    String sumMoey(String appKey);

    List<Map> everydayMoney(String appKey);

    String countOrder(String appKey);

}
