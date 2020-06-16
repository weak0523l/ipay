package com.example.ipay.util;


import com.example.ipay.service.impl.PayMerchantServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUpload {
    private static final Logger logger = LoggerFactory.getLogger(PayMerchantServiceImpl.class);
    private static final String URL = "E:\\WenJian\\";

    public String uploadFile(MultipartFile file){
        if (file.isEmpty()) {
            return "上传失败";
        }
        String fileName = file.getOriginalFilename(); //文件名

        String filePath = URL;//上传后的路径

        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);

            logger.info("上传成功");

            return filePath;

        } catch (IOException e) {

            logger.error("上传失败");

            e.printStackTrace();

        }
        return filePath;
    }
}
