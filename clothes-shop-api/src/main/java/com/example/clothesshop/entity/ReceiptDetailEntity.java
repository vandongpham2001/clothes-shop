package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ReceiptDetail")
@Data
public class ReceiptDetailEntity extends BaseEntity {
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private ReceiptEntity receipt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_size_id")
    private ProductColorSizeEntity product_color_size;
}
