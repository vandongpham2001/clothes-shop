package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProductColorSize")
@Data
public class ProductColorSizeEntity extends BaseEntity {
    private Integer quantity;
//    private Integer status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_id")
    private ProductColorEntity product_color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private SizeEntity size;
    @OneToMany(
            mappedBy = "product_color_size",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<OrderDetailEntity> order_detail = new ArrayList<>();
    @OneToMany(
            mappedBy = "product_color_size",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<CartEntity> carts = new ArrayList<>();
    @OneToMany(
            mappedBy = "product_color_size",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<ReceiptDetailEntity> receipt_detail = new ArrayList<>();
}
