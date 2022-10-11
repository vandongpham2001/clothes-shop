package com.example.clothesshop.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductColorImageRequest extends AbstractRequest<ProductColorImageRequest> {
    private Long product_color_id;
    private MultipartFile[] file;
}
