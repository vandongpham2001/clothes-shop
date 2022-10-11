package com.example.clothesshop.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderRequest extends AbstractRequest<OrderRequest> {
    private Integer status;
    private Date payment_time;
    private Integer payment_method;
    private Long user_id;
    private List<OrderDetailRequest> order_detail = new ArrayList<>();
}
