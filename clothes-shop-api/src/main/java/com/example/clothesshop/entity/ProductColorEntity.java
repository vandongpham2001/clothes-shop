package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProductColor")
@Data
public class ProductColorEntity extends BaseEntity{
//    private Integer status;
    private String thumbnail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private ColorEntity color;
    @OneToMany(
//            fetch = FetchType.EAGER,
            mappedBy = "product_color",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<ProductColorImageEntity> product_color_image = new ArrayList<>();
    @OneToMany(
//            fetch = FetchType.EAGER,
            mappedBy = "product_color",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<ProductColorSizeEntity> product_color_size = new ArrayList<>();
}
