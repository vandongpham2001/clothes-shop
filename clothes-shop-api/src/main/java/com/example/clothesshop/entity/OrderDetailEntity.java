package com.example.clothesshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetail")
public class OrderDetailEntity extends BaseEntity {
    private Integer quantity;
    private BigDecimal selling_price;
    //    private Float discount_percent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_color_size_id")
    private ProductColorSizeEntity product_color_size;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(BigDecimal selling_price) {
        this.selling_price = selling_price;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductColorSizeEntity getProduct_color_size() {
        return product_color_size;
    }

    public void setProduct_color_size(ProductColorSizeEntity product_color_size) {
        this.product_color_size = product_color_size;
    }
}
