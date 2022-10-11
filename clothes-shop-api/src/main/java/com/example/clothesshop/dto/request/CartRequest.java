package com.example.clothesshop.dto.request;

import lombok.Data;

@Data
public class CartRequest extends AbstractRequest<CartRequest>{
    private Integer quantity;
    private Boolean checked;
    private Long user_id;
    private Long product_color_size_id;
}
