package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

@Data
public class ProductColorImageDTO extends AbstractDTO<ProductColorImageDTO> {
    private String path;
    private Long product_color_id;
//    @JsonManagedReference
//    private ProductColorDTO product_color;
}
