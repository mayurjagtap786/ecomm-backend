package com.example.order.mapper;

import com.example.order.dto.OrderRecord;
import com.example.order.entity.Order;
import com.example.order.enums.OrderStatus;

import java.util.UUID;

public class OrderMapper {

    public static Order orderRecordToOrderEntity(OrderRecord orderRecord, Order order){
        order.setOrderId(UUID.randomUUID().toString());
        order.setProductId(orderRecord.productId());
        order.setQuantity(orderRecord.quantity());
        order.setStatus(OrderStatus.CREATED);
        order.setAmount(orderRecord.amount());
        return order;
    }
}
