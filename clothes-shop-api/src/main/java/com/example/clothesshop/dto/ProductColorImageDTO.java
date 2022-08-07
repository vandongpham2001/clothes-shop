package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class ProductColorImageDTO extends AbstractDTO<ProductColorImageDTO> {
    private String name;
    private String path;
    private ProductColorDTO product_color;
}
