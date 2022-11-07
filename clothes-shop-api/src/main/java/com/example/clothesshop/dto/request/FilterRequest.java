package com.example.clothesshop.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FilterRequest {
    private BigDecimal min_price;
    private BigDecimal max_price;
    private Long color_id;
    private  Long size_id;

}
