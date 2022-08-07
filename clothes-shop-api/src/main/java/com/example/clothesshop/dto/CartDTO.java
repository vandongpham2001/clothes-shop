package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class CartDTO extends AbstractDTO<CartDTO> {
    private Integer quantity;
    private Boolean checked;
    private UserDTO user;
    private ProductColorSizeDTO product_color_size;
}
