package com.example.clothesshop.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String image;
    private Double discount;
    private BigDecimal salePrice;
    private Integer color;
//    private CategoryResponse category;
    private Set<PromotionResponse> promotions = new HashSet<>();
//    private List<SizeOfProductColorResponse> product_color;
    private Date createdDate;
}
