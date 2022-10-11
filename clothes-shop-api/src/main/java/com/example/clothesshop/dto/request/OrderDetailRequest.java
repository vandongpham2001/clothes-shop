package com.example.clothesshop.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailRequest extends AbstractRequest<OrderDetailRequest> {
    private Integer quantity;
    private BigDecimal selling_price;
    private Long order_id;
    private Long product_color_size_id;
}
