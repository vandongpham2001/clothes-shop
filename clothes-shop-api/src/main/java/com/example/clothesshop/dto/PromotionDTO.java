package com.example.clothesshop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile mobile_banner_file;
    private MultipartFile pc_banner_file;
    private String mobile_banner;
    private String pc_banner;
    private List<ProductDTO> products = new ArrayList<>();
}
