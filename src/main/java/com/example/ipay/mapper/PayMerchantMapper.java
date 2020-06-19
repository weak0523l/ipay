package com.example.ipay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ipay.bean.PayMerchant;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;


public interface PayMerchantMapper extends BaseMapper<PayMerchant> {
     @Update("UPDATE Pay_Merchant SET CertPath=#{filePath} WHERE id =#{id}")
     boolean upload(String filePath, String id);

     @Select("SELECT SUM(Money) from Pay_Orders WHERE AppKey=#{appKey} and PayState='1'" )
     String sumMoney (String appKey);

     @Select("SELECT YEAR\n" +
             "\t( CreateTime ) 年,\n" +
             "\tMONTH ( CreateTime ) 月,\n" +
             "\tDAY ( CreateTime ) 日,\n" +
             "\tSUM ( Money ) 收入合计 \n" +
             "FROM\n" +
             "\tPay_Orders \n" +
             " WHERE AppKey=#{appKey} and PayState='1'" +
             "GROUP BY\n" +
             "\tYEAR ( CreateTime ),\n" +
             "\tMONTH ( CreateTime ),\n" +
             "\tDAY (CreateTime)")
     List<Map> everydayMoney(String appKey);



/*     String map<>*/
}
