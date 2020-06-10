package com.example.ipay.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@ApiModel("商户")
public class PayMerchant implements Serializable {

    private String Id;
    private String Appkey;
    private String Merchname;
    private String Swiffpassmerchno;
    private String Swiffpasspaysecert;
    private Integer Openchannel;
    private String Merid;
    private String Merinst;
    private String Certpath;
    private String Certpwd;
    private String Createuser;
    private Date Createtime;
    @TableField(exist = false)//该表中不存在字段
    private List<com.example.ipay.bean.PayOrders> payOrdersList;
}
