package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


public class SizeDTO extends AbstractDTO<SizeDTO> {
    String size;
    @JsonIgnore
    private List<ProductColorSizeDTO> products = new ArrayList<>();

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<ProductColorSizeDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductColorSizeDTO> products) {
        this.products = products;
    }
}
