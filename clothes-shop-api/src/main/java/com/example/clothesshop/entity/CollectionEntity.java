package com.example.clothesshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Collections")
//@Data
@Getter
@Setter
public class CollectionEntity extends BaseEntity{
    private String name;
    private Integer status;
    private String mobile_banner;
    private String pc_banner;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "collections")
    private List<ProductEntity> products = new ArrayList<>();
    public void addProduct(ProductEntity product) {
        this.products.add(product);
        product.getCollections().add(this);
    }
}
