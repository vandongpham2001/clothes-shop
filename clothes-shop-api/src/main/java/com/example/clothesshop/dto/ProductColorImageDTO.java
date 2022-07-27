package com.example.clothesshop.dto;

public class ProductColorImageDTO extends AbstractDTO<ProductColorImageDTO> {
    private String name;
    private String path;
    private ProductColorDTO product_color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProductColorDTO getProduct_color() {
        return product_color;
    }

    public void setProduct_color(ProductColorDTO product_color) {
        this.product_color = product_color;
    }
}
