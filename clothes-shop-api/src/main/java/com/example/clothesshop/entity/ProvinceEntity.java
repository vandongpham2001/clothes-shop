package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Provinces")
@Data
public class ProvinceEntity {
    @Id
    private String code;
    private String name;
    private String name_en;
    private String full_name;
    private String full_name_en;
    private String code_name;
    @ManyToOne
    @JoinColumn(name = "administrative_region_id")
    private AdministrativeRegionEntity administrative_region;
    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnitEntity administrative_unit;
    @OneToMany(
            mappedBy = "province",
            cascade = CascadeType.ALL
    )
    private List<DistrictEntity> districts = new ArrayList<>();
//    private String administrative_unit_id;
//    private String administrative_region_id;
}
