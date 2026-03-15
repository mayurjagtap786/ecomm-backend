package com.example.order.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix="order")
public record OrderServiceContactInfo(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
}
