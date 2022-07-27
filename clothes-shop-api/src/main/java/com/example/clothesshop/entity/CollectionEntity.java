package com.example.clothesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Collections")
public class CollectionEntity extends BaseEntity{
    private String name;
    private Integer status;
    private String mobile_banner;
    private String pc_banner;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "collections")
    private List<ProductEntity> products = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile_banner() {
        return mobile_banner;
    }

    public void setMobile_banner(String mobile_banner) {
        this.mobile_banner = mobile_banner;
    }

    public String getPc_banner() {
        return pc_banner;
    }

    public void setPc_banner(String pc_banner) {
        this.pc_banner = pc_banner;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
