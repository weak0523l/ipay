package com.example.ipay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ipay.bean.PayMerchant;
import org.apache.ibatis.annotations.Update;


public interface PayMerchantMapper extends BaseMapper<PayMerchant> {
     @Update("UPDATE Pay_Merchant SET CertPath=#{filePath} WHERE id =#{id}")
     boolean upload(String filePath, String id);
}
