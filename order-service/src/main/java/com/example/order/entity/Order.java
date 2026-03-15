package com.example.order.entity;

import com.example.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    private Long id;

    private String orderId;

    @Column(unique = true)
    private String productId;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Double amount;

}
