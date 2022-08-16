package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class CartDTO extends AbstractDTO<CartDTO> {
    private Integer quantity;
    private Boolean checked;
    private Long user_id;
    private Long product_color_size_id;
}
