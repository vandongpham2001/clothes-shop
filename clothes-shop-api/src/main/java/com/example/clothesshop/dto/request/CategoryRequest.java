package com.example.clothesshop.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryRequest {
    private Long id;
    private String name;
    private String text;
    private String description;
    private String image;
    private String slug;
    private Integer status;
    private Long parent_id;
    private MultipartFile[] file;
}
