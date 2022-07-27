package com.example.clothesshop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProductColor")
public class ProductColorEntity extends BaseEntity{
    private Integer status;
    private String thumbnail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private ColorEntity color;
    @OneToMany(
            mappedBy = "product_color",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductColorImageEntity> product_color_image = new ArrayList<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public ColorEntity getColor() {
        return color;
    }

    public void setColor(ColorEntity color) {
        this.color = color;
    }

    public List<ProductColorImageEntity> getProduct_color_image() {
        return product_color_image;
    }

    public void setProduct_color_image(List<ProductColorImageEntity> product_color_image) {
        this.product_color_image = product_color_image;
    }
}
