package com.example.clothesshop.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

@Data
public class ProductColorSizeResponse {
    private Long id;
//    @JsonManagedReference
//    private ProductColorResponse product_color;
    private SizeResponse size;
    private Integer quantity;
}
