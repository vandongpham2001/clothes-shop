package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class ProductColorSizeDTO extends AbstractDTO<ProductColorSizeDTO> {
    private Integer quantity;
    private Long product_color_id;
    private Long size_id;
}
