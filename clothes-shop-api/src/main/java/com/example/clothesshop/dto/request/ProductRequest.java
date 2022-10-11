package com.example.clothesshop.dto.request;

import com.example.clothesshop.dto.CategoryDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductRequest extends AbstractRequest<ProductRequest> {
    private String name;
    private BigDecimal price;
    private String description;
    private Integer status;
    private Float avg_rating;
    private Long category_id;
    private List<ProductColorRequest> product_color = new ArrayList<>();

}
