package com.example.clothesshop.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductColorResponse {
    private Long id;
//    @JsonManagedReference
//    private ProductResponse product;
    private ColorResponse color;
    private String thumbnail;
    List<ProductColorSizeResponse> product_color_size = new ArrayList<>();
    List<ProductColorImageResponse> product_color_image = new ArrayList<>();
}
