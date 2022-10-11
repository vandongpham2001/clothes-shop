package com.example.clothesshop.dto.request;

import lombok.Data;

@Data
public class ProductColorImageRequest extends AbstractRequest<ProductColorImageRequest> {
    private Long product_color_id;
}
