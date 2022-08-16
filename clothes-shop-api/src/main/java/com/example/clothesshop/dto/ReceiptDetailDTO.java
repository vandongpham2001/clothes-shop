package com.example.clothesshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceiptDetailDTO extends AbstractDTO<ReceiptDetailDTO> {
    private Integer quantity;
    private BigDecimal price;
    private Long receipt_id;
    private Long product_color_size_id;
}
