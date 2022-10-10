package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Carts")
@Data
public class CartEntity extends BaseEntity {
    private Integer quantity;
    private Boolean checked;
    //    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    //    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "product_color_size_id")
    private ProductColorSizeEntity product_color_size;
}
