package com.example.clothesshop.dto.request;

import lombok.Data;

@Data
public class ProductToPromotion {
    private Long promotion_id;
    private Long[] product_id;
}
