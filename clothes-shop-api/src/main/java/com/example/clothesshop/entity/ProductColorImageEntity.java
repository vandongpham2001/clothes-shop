package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ProductColorImage")
@Data
public class ProductColorImageEntity extends BaseEntity {
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_id")
    private ProductColorEntity product_color;
}
