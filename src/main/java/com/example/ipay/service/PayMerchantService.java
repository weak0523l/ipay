package com.example.ipay.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ipay.bean.PayMerchant;


public interface PayMerchantService extends IService<PayMerchant> {

    boolean  insertList(PayMerchant payMerchant);

    boolean delectById(String id);

    boolean updateMerById(PayMerchant payMerchant);


}
