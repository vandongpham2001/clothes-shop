package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class ProductColorSizeDTO extends AbstractDTO<ProductColorSizeDTO> {
    private Integer quantity;
    private Integer status;
    private ProductColorDTO product_color;
    private SizeDTO size;
}
