package com.example.ipay.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ipay.bean.PayMerchant;
import com.example.ipay.mapper.PayMerchantMapper;
import com.example.ipay.service.PayMerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class PayMerchantServiceImpl extends ServiceImpl<PayMerchantMapper, PayMerchant> implements PayMerchantService {
    private static final Logger logger = LoggerFactory.getLogger(PayMerchantServiceImpl.class);
    private static final String URL = "E:\\WenJian\\";
    @Autowired
    PayMerchantMapper payMerchantMapper;

    @Override
    public String insertList(PayMerchant payMerchant, MultipartFile file) {
        if(file.isEmpty()){
            return "上传失败";
        }
        String fileName = file.getOriginalFilename(); //文件名

        String filePath = URL;//上传后的路径

        File dest = new File(filePath+fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);

            logger.info("上传成功");

            payMerchant.setCertpath(filePath);

            payMerchantMapper.insert(payMerchant);

            return filePath;

        }catch (IOException e){

            logger.error("上传失败");

            e.printStackTrace();

        }

        return "success";
    }

    @Override
    public void delectById(String id) { payMerchantMapper.deleteById(id); }

    @Override
    public void update(PayMerchant payMerchant) { payMerchantMapper.updateById(payMerchant); }

    @Override
    public List<PayMerchant> getPage(String pagenow, String pagecount) {

        Page<PayMerchant> payMerchantPage = new Page<>(Integer.parseInt(pagenow),Integer.parseInt(pagecount));

        IPage<PayMerchant> payMerchantIPage = payMerchantMapper.selectPage(payMerchantPage, null);

        List<PayMerchant> payMerchant = payMerchantIPage.getRecords();

        return payMerchant;
    }



}
