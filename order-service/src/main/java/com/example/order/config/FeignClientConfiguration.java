package com.example.order.config;

import com.example.order.exception.CustomErrorDecorator;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    @Bean
    public CustomErrorDecorator errorDecorator(){
        return new CustomErrorDecorator();
    }


}
