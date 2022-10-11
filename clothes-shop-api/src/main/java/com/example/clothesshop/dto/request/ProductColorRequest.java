package com.example.clothesshop.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductColorRequest extends AbstractRequest<ProductColorRequest> {
    private Long product_id;
    private Long color_id;
    private List<ProductColorImageRequest> product_color_image = new ArrayList<>();
    private List<ProductColorSizeRequest> product_color_size = new ArrayList<>();
}
