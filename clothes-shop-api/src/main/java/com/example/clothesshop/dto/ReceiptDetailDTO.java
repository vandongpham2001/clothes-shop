package com.example.clothesshop.dto;

import java.math.BigDecimal;


public class ReceiptDetailDTO extends AbstractDTO<ReceiptDetailDTO> {
    private Integer quantity;
    private BigDecimal price;
    private ReceiptDTO receipt;
    private ProductColorSizeDTO product_color_size;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ReceiptDTO getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptDTO receipt) {
        this.receipt = receipt;
    }

    public ProductColorSizeDTO getProduct_color_size() {
        return product_color_size;
    }

    public void setProduct_color_size(ProductColorSizeDTO product_color_size) {
        this.product_color_size = product_color_size;
    }
}
