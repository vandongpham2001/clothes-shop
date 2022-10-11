package com.example.clothesshop.dto.request;

import lombok.Data;

@Data
public class ProductColorSizeRequest extends AbstractRequest<ProductColorSizeRequest> {
    private Integer quantity;
    private Long product_color_id;
    private Long size_id;
}
