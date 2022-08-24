package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Float avg_rating;
    private String image;
    private Long category_id;
    @JsonManagedReference
    private CategoryDTO category;
//    @JsonIgnore
//    @JsonBackReference
//    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductColorDTO> product_color = new ArrayList<>();
//    @JsonIgnore
//    private List<CommentDTO> comments = new ArrayList<>();
//    @JsonIgnore
//    private List<CollectionDTO> collections = new ArrayList<>();
//    @JsonIgnore
//    private List<PromotionDTO> promotions = new ArrayList<>();
}
