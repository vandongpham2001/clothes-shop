package com.example.clothesshop.dto;

import java.util.ArrayList;
import java.util.List;

public class CollectionDTO extends AbstractDTO<CollectionDTO>{
    private String name;
    private Integer status;
    private String mobile_banner;
    private String pc_banner;
    private List<ProductDTO> products = new ArrayList<>();

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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
