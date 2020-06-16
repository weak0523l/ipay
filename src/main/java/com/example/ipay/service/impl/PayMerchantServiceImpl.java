package com.example.ipay.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ipay.bean.PayMerchant;
import com.example.ipay.mapper.PayMerchantMapper;
import com.example.ipay.service.PayMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PayMerchantServiceImpl extends ServiceImpl<PayMerchantMapper, PayMerchant> implements PayMerchantService {

    @Autowired
    PayMerchantMapper payMerchantMapper;

    @Override
    public boolean insertList(PayMerchant payMerchant) {
        payMerchant.setId(UUID.randomUUID().toString());

        payMerchantMapper.insert(payMerchant);

        return true;
    }

    @Override
    public boolean delectById(String id) {
        payMerchantMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateMerById(PayMerchant payMerchant) {


        payMerchantMapper.updateById(payMerchant);

        return true;

    }



}
