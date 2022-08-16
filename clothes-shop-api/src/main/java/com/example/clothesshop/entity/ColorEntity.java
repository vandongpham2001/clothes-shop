package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Colors")
@Data
public class ColorEntity extends BaseEntity {
    private String color;
    @OneToMany(
            mappedBy = "color",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<ProductColorEntity> product_color = new ArrayList<>();
}
