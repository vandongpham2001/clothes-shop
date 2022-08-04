package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Promotions")
@Data
public class PromotionEntity extends BaseEntity {
    private String name;
    private Date start_date;
    private Date end_date;
    private Float discount;
    private String slug;
    private Integer status;
    private String mobile_banner;
    private String pc_banner;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "promotions")
    private List<ProductEntity> products = new ArrayList<>();
}
