package com.example.clothesshop.dto;

import java.math.BigDecimal;

public class OrderDetailDTO extends AbstractDTO<OrderDetailDTO>{
    private Integer quantity;
    private BigDecimal selling_price;
    private OrderDTO order;
    private ProductColorSizeDTO product_color_size;

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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public ProductColorSizeDTO getProduct_color_size() {
        return product_color_size;
    }

    public void setProduct_color_size(ProductColorSizeDTO product_color_size) {
        this.product_color_size = product_color_size;
    }
}
