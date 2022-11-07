package com.example.clothesshop.dto.response;

import lombok.Data;

@Data
public class ProductColorImageResponse {
    private Long id;
    private String path;
//    @JsonManagedReference
//    private ProductColorResponse product_color;
}
