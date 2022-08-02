package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


public class ProductColorDTO extends AbstractDTO<ProductColorDTO> {
    private Integer status;
    private String thumbnail;
    private ProductDTO product;
    private ColorDTO color;
    @JsonIgnore
    private List<ProductColorImageDTO> product_color_image = new ArrayList<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public ColorDTO getColor() {
        return color;
    }

    public void setColor(ColorDTO color) {
        this.color = color;
    }

    public List<ProductColorImageDTO> getProduct_color_image() {
        return product_color_image;
    }

    public void setProduct_color_image(List<ProductColorImageDTO> product_color_image) {
        this.product_color_image = product_color_image;
    }
}
