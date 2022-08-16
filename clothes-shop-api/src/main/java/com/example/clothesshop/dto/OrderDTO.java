package com.example.clothesshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO extends AbstractDTO<OrderDTO> {
    private Integer status;
    private Date time_order;
    private Date payment_time;
    private Integer payment_method;
    private Long user_id;
    private List<OrderDetailDTO> order_detail = new ArrayList<>();
}
