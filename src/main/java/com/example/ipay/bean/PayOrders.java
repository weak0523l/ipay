package com.example.ipay.bean;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("订单")
public class PayOrders implements Serializable {
    private String Id;
    private String Orderno;
    private BigDecimal Money;
    private String Appkey;
    private String Authcode;
    private String Operauser;
    private Integer Paystate;
    private Date Paydate;
    private String Paytype;
    private Integer Paychannel;
    private Date Createtime;

}
