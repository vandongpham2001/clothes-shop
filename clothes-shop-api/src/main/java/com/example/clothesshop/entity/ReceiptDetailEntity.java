package com.example.clothesshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ReceiptDetail")
public class ReceiptDetailEntity extends BaseEntity {
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private ReceiptEntity receipt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_size_id")
    private ProductColorSizeEntity product_color_size;

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

    public ReceiptEntity getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptEntity receipt) {
        this.receipt = receipt;
    }

    public ProductColorSizeEntity getProduct_color_size() {
        return product_color_size;
    }

    public void setProduct_color_size(ProductColorSizeEntity product_color_size) {
        this.product_color_size = product_color_size;
    }
}
