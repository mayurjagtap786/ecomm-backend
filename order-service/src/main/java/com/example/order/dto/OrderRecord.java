package com.example.order.dto;

public record OrderRecord(String productId, Integer quantity,Double amount) {}

    /*private String productId;
    private int quantity;
    private Double amount;

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}*/
