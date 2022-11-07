package com.example.clothesshop.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class PromotionResponse {
    private Long id;
    private String name;
    private Date start_date;
    private Date end_date;
    private Float discount;
    private String mobile_banner;
    private String pc_banner;
    private Integer status;
}
