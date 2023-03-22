package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AdministrativeRegions")
@Data
public class AdministrativeRegionEntity {
    @Id
    private Long id;
    private String name;
    private String name_en;
    private String code_name;
    private String code_name_en;
    @OneToMany(
            mappedBy = "administrative_region",
            cascade = CascadeType.ALL
    )
    private List<ProvinceEntity> provinces = new ArrayList<>();
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
//    @ManyToOne
//    @JoinColumn(name = "product_color_size_id")
//    private ProductColorSizeEntity product_color_size;

}
