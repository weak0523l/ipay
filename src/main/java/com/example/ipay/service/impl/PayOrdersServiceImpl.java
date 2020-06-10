package com.example.ipay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ipay.bean.PayOrders;
import com.example.ipay.mapper.PayOrdersMapper;

import com.example.ipay.service.PayOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayOrdersServiceImpl extends ServiceImpl<PayOrdersMapper, PayOrders> implements PayOrdersService {

    @Autowired
    PayOrdersMapper payOrdersBeanMapper;

    @Override
    public List<PayOrders> getPageOrder(String pagenow, String pagecount, String appkey) {

        Page<PayOrders> payOrdersPage =new Page<>(Integer.parseInt(pagenow),Integer.parseInt(pagecount));

        QueryWrapper<PayOrders> wrapper = new QueryWrapper<>();

        wrapper.eq("Appkey",appkey);

        IPage<PayOrders> payOrdersIPage = payOrdersBeanMapper.selectPage(payOrdersPage,wrapper);

        List<PayOrders> payOrders = payOrdersIPage.getRecords();

        return payOrders;
    }

    @Override
    public List<PayOrders> list(String pagenow, String pagecount) {

        Page<PayOrders> page = new Page<>(Integer.parseInt(pagenow),Integer.parseInt(pagecount));

        IPage<PayOrders> iPage = payOrdersBeanMapper.selectPage(page,null);

        List<PayOrders> records = iPage.getRecords();
        return records;
    }


}
