package com.example.clothesshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "ProductColorSize")
public class ProductColorSizeEntity extends BaseEntity {
    private Integer quantity;
    private Integer status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_id")
    private ProductColorEntity product_color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ProductColorEntity getProduct_color() {
        return product_color;
    }

    public void setProduct_color(ProductColorEntity product_color) {
        this.product_color = product_color;
    }

    public SizeEntity getSize() {
        return size;
    }

    public void setSize(SizeEntity size) {
        this.size = size;
    }
}
