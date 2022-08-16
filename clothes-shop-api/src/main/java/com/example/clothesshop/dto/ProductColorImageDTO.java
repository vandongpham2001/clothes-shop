package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class ProductColorImageDTO extends AbstractDTO<ProductColorImageDTO> {
    private String path;
    private Long product_color_id;
}
