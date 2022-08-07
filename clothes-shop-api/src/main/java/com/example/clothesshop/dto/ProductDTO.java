package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO extends AbstractDTO<ProductDTO> {
    private String name;
    private BigDecimal price;
    private String description;
    private String slug;
    private Integer status;
    private float avg_rating;
    private String image;

    private CategoryDTO category;
    @JsonIgnore
    private List<CommentDTO> comments = new ArrayList<>();
    @JsonIgnore
    private List<ProductColorDTO> product_color = new ArrayList<>();

    private List<CollectionDTO> collections = new ArrayList<>();

    private List<PromotionDTO> promotions = new ArrayList<>();
}
