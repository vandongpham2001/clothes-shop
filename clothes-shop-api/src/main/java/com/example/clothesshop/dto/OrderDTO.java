package com.example.clothesshop.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO extends AbstractDTO<OrderDTO> {
    private Integer status;
    private Date time_order;
    private Date payment_time;
    private Integer payment_method;
    private UserDTO user;
    private List<OrderDetailDTO> order_detail = new ArrayList<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime_order() {
        return time_order;
    }

    public void setTime_order(Date time_order) {
        this.time_order = time_order;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public Integer getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(Integer payment_method) {
        this.payment_method = payment_method;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<OrderDetailDTO> getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(List<OrderDetailDTO> order_detail) {
        this.order_detail = order_detail;
    }
}
