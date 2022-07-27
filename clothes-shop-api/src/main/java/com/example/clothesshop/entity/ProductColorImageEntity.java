package com.example.clothesshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "ProductColorImage")
public class ProductColorImageEntity extends BaseEntity {
    private String name;
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_id")
    private ProductColorEntity product_color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProductColorEntity getProduct_color() {
        return product_color;
    }

    public void setProduct_color(ProductColorEntity product_color) {
        this.product_color = product_color;
    }
}
