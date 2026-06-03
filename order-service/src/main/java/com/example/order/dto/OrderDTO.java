package com.example.order.dto;

import com.example.order.enums.OrderStatus;

public class OrderDTO {

    private String orderid;
    private String productid;
    private Integer quantity;
    private Double amount;
    private OrderStatus orderStatus;

    public OrderDTO() {
    }

    public String getOrderid() {
        return orderid;
    }

    public String getProductid() {
        return productid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderDTO(String orderid, String productid, Integer quantity, Double amount, OrderStatus orderStatus) {
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }


    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderid='" + orderid + '\'' +
                ", productid='" + productid + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
