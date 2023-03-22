package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Districts")
@Data
public class DistrictEntity {
    @Id
    private String code;
    private String name;
    private String name_en;
    private String full_name;
    private String full_name_en;
    private String code_name;
    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnitEntity administrative_unit;
    @ManyToOne
    @JoinColumn(name = "province_code")
    private ProvinceEntity province;
    @OneToMany(
            mappedBy = "district",
            cascade = CascadeType.ALL
    )
    private List<WardEntity> wards = new ArrayList<>();
//    private String province_code;
//    private String administrative_unit_id;
}
