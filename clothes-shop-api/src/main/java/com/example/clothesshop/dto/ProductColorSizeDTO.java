package com.example.clothesshop.dto;

public class ProductColorSizeDTO extends AbstractDTO<ProductColorSizeDTO> {
    private Integer quantity;
    private Integer status;
    private ProductColorDTO product_color;
    private SizeDTO size;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ProductColorDTO getProduct_color() {
        return product_color;
    }

    public void setProduct_color(ProductColorDTO product_color) {
        this.product_color = product_color;
    }

    public SizeDTO getSize() {
        return size;
    }

    public void setSize(SizeDTO size) {
        this.size = size;
    }
}
