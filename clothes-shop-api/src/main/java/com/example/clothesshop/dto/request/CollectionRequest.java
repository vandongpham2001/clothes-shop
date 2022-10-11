package com.example.clothesshop.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class CollectionRequest extends AbstractRequest<CollectionRequest> {
    private String name;
    private Integer status;
    private MultipartFile mobile_banner_file;
    private MultipartFile pc_banner_file;
}
