package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class CategoryDTO extends AbstractDTO<CategoryDTO> {
    private String name;
    private String text;
    private String description;
    private String image;
    private String slug;
    private Integer status;
    private Long parent_id;
//    @JsonIgnore
//    private List<ProductDTO> products = new ArrayList<>();
}
