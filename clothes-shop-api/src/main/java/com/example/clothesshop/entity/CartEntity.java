package com.example.clothesshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "Carts")
public class CartEntity extends BaseEntity {
    private Integer quantity;
    private Boolean checked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_size_id")
    private ProductColorSizeEntity product_color_size;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProductColorSizeEntity getProduct_color_size() {
        return product_color_size;
    }

    public void setProduct_color_size(ProductColorSizeEntity product_color_size) {
        this.product_color_size = product_color_size;
    }
}
