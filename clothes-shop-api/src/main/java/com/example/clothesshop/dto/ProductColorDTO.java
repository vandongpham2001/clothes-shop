package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductColorDTO extends AbstractDTO<ProductColorDTO> {
    private String thumbnail;
    private Long product_id;
    private Long color_id;
//    @JsonManagedReference
//    private ColorDTO color;
//    @JsonBackReference
    private List<ProductColorImageDTO> product_color_image = new ArrayList<>();
//    @JsonBackReference
    private List<ProductColorSizeDTO> product_color_size = new ArrayList<>();
}
