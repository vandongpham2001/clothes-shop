package com.example.clothesshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Colors")
public class ColorEntity extends BaseEntity {
    private String color;
    @OneToMany(
            mappedBy = "color",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductColorEntity> product_color = new ArrayList<>();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ProductColorEntity> getProduct_color() {
        return product_color;
    }

    public void setProduct_color(List<ProductColorEntity> product_color) {
        this.product_color = product_color;
    }
}
