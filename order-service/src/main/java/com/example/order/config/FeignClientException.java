package com.example.order.config;

import com.example.order.exception.CustomErrorDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientException {

    @Bean
    public CustomErrorDecorator errorDecorator(){
        return new CustomErrorDecorator();
    }
}
