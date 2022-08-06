package com.example.clothesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    String size;
    @OneToMany(
            mappedBy = "size",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    @JsonIgnore
    private List<ProductColorSizeEntity> products = new ArrayList<>();
}
