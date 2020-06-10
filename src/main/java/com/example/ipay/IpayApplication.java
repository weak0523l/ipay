package com.example.ipay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ipay.mapper")
public class IpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpayApplication.class, args);
    }

}
