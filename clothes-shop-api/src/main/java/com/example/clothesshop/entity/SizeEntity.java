package com.example.clothesshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Sizes")
public class SizeEntity extends BaseEntity {
    String size;
    @OneToMany(
            mappedBy = "size",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductColorSizeEntity> products = new ArrayList<>();

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<ProductColorSizeEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductColorSizeEntity> products) {
        this.products = products;
    }
}
