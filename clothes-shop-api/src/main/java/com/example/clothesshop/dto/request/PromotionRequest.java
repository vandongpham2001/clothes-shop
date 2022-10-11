package com.example.clothesshop.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PromotionRequest extends AbstractRequest<PromotionRequest> {
    private String name;
    private Date start_date;
    private Date end_date;
    private Float discount;
    private Integer status;
    private MultipartFile mobile_banner_file;
    private MultipartFile pc_banner_file;
}
