package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductColorDTO extends AbstractDTO<ProductColorDTO> {
    private Integer status;
    private String thumbnail;
    private ProductDTO product;
    private ColorDTO color;
    @JsonIgnore
    private List<ProductColorImageDTO> product_color_image = new ArrayList<>();
}
