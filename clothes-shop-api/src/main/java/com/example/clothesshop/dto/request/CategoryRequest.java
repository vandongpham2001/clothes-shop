package com.example.clothesshop.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryRequest extends AbstractRequest<CategoryRequest>{
    private String name;
    private String text;
    private String description;
    private Integer status;
    private Long parent_id;
    private MultipartFile file;
}
