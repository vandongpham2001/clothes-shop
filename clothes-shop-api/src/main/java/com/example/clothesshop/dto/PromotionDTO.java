package com.example.clothesshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PromotionDTO extends AbstractDTO<PromotionDTO> {
    private String name;
    private Date start_date;
    private Date end_date;
    private Float discount;
    private String slug;
    private Integer status;
    private String mobile_banner;
    private String pc_banner;
    private List<ProductDTO> products = new ArrayList<>();
}
