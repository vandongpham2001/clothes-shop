package com.example.clothesshop.dto.request;

import lombok.Data;

@Data
public class ProductToCollection {
    private Long[] product_id;
    private Long collection_id;
}
