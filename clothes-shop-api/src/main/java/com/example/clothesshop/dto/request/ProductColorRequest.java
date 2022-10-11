package com.example.clothesshop.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductColorRequest extends AbstractRequest<ProductColorRequest> {
    private Long product_id;
    private Long color_id;
    private MultipartFile file;
    private List<ProductColorImageRequest> product_color_image = new ArrayList<>();
    private List<ProductColorSizeRequest> product_color_size = new ArrayList<>();
}
