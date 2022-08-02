package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetail")
@Data
public class OrderDetailEntity extends BaseEntity {
    private Integer quantity;
    private BigDecimal selling_price;
    //    private Float discount_percent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_size_id")
    private ProductColorSizeEntity product_color_size;
}
