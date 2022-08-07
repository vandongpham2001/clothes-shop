package com.example.clothesshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDTO extends AbstractDTO<OrderDetailDTO> {
    private Integer quantity;
    private BigDecimal selling_price;
    private OrderDTO order;
    private ProductColorSizeDTO product_color_size;
}
