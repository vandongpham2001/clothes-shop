package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Sizes")
@Data
public class SizeEntity extends BaseEntity {
    private String size;
    private Integer status;
    @OneToMany(
            mappedBy = "size",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<ProductColorSizeEntity> products = new ArrayList<>();
}
