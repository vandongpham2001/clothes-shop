package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Wards")
@Data
public class WardEntity {
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
    @JoinColumn(name = "district_code")
    private DistrictEntity district;
    @OneToMany(
            mappedBy = "ward",
            cascade = CascadeType.ALL
    )
    private List<UserEntity> wards = new ArrayList<>();
//    private String district_code;
//    private String administrative_unit_id;
}
