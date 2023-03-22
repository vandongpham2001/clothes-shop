package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AdministrativeUnits")
@Data
public class AdministrativeUnitEntity {
    @Id
    private Long id;
    private String full_name;
    private String full_name_en;
    private String short_name;
    private String short_name_en;
    private String code_name;
    private String code_name_en;
    @OneToMany(
            mappedBy = "administrative_unit",
            cascade = CascadeType.ALL
    )
    private List<ProvinceEntity> provinces = new ArrayList<>();
    @OneToMany(
            mappedBy = "administrative_unit",
            cascade = CascadeType.ALL
    )
    private List<DistrictEntity> districts = new ArrayList<>();
    @OneToMany(
            mappedBy = "administrative_unit",
            cascade = CascadeType.ALL
    )
    private List<WardEntity> wards = new ArrayList<>();
}
