package com.example.ipay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ipay.bean.PayOrders;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface PayOrdersMapper extends BaseMapper<PayOrders> {

    @Select("SELECT pm.MerchName,po.* from Sys_Users su\n" +
            "INNER JOIN Pay_Merchant pm on su.MerchantId = pm.ID\n" +
            "INNER JOIN Pay_Orders po on pm.Appkey = po.AppKey \n" +
            "WHERE su.MerchantId =#{MerchantId}")
    List<PayOrders> listById(String MerchantId);





}
