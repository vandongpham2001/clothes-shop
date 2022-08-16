package com.example.clothesshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductColorDTO extends AbstractDTO<ProductColorDTO> {
    private Integer status;
    private String thumbnail;
    private Long product_id;
    private Long color_id;
    private List<ProductColorImageDTO> product_color_image = new ArrayList<>();
    private List<ProductColorSizeDTO> product_color_size = new ArrayList<>();
}
