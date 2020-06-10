package com.example.ipay.conf;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    //注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){ return new OptimisticLockerInterceptor(); }
    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){ return new PaginationInterceptor(); }
    //逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector() { return new LogicSqlInjector(); }


    //SQL执行效率插件
  /*  @Bean
    @Profile({"dev","test"})  // 设置dev test 环境开启,保证我们的效率
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor =new PerformanceInterceptor();
        //在工作中，不允许用户等待
        performanceInterceptor.setMaxTime(100);//ms(毫秒) 设置SQL的最大执行时间，如果超过了就不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }*/
}
