package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private UserDTO user;
    @JsonIgnore
    private List<OrderDetailDTO> order_detail = new ArrayList<>();
}
