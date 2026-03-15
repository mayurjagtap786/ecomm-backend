package com.example.order.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    private String api;
    private String status;
    private String message;
    private LocalDateTime time;

}
