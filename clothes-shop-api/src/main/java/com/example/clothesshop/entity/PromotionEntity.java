package com.example.clothesshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Promotions")
//@Data
@Getter
@Setter
public class PromotionEntity extends BaseEntity {
    private String name;
    private Date start_date;
    private Date end_date;
    private Float discount;
    private String slug;
    private Integer status;
    private String mobile_banner;
    private String pc_banner;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "promotions")
    private List<ProductEntity> products = new ArrayList<>();
    public void addProduct(ProductEntity product) {
        this.products.add(product);
        product.getPromotions().add(this);
    }
}
