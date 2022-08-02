package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ColorDTO extends AbstractDTO<ColorDTO>{
    private String color;
    @JsonIgnore
    private List<ProductColorDTO> product_color = new ArrayList<>();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ProductColorDTO> getProduct_color() {
        return product_color;
    }

    public void setProduct_color(List<ProductColorDTO> product_color) {
        this.product_color = product_color;
    }
}
