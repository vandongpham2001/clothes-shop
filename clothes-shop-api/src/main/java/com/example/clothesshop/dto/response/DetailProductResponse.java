package com.example.clothesshop.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class DetailProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String image;
    private Double discount;
    private BigDecimal salePrice;
    private CategoryResponse category;
    private List<ProductColorResponse> product_color;
//    @Where(clause = "status=1 and current_date between start_date and end_date")
    private Set<PromotionResponse> promotions = new HashSet<>();
}
