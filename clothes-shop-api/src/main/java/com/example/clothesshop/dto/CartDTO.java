package com.example.clothesshop.dto;

public class CartDTO extends AbstractDTO<CartDTO> {
    private Integer quantity;
    private Boolean checked;
    private UserDTO user;
    private ProductColorSizeDTO product_color_size;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ProductColorSizeDTO getProduct_color_size() {
        return product_color_size;
    }

    public void setProduct_color_size(ProductColorSizeDTO product_color_size) {
        this.product_color_size = product_color_size;
    }
}
