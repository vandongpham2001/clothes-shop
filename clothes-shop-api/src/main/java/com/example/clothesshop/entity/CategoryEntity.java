package com.example.clothesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categories")
@Data
public class CategoryEntity extends BaseEntity {
    private String name;
    private String text;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    private String slug;
    private Integer status;
    private Long parent_id;
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<ProductEntity> products = new ArrayList<>();
}
