package com.example.clothesshop.dto.response;

import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String text;
    private String description;
    private String image;
}
