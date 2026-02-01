package com.example.order.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecorator implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        System.out.println("Response in custom error decoration :"+response);
        return null;
    }
}
