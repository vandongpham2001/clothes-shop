package com.example.clothesshop.dto;

import java.util.ArrayList;
import java.util.List;


public class SizeDTO extends AbstractDTO<SizeDTO> {
    String size;
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
