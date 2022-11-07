package com.example.clothesshop.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Products")
//@Data
@Getter
@Setter
public class ProductEntity extends BaseEntity{
    private String name;
    private BigDecimal price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String slug;
    private Integer status;
    private Float avg_rating;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<CommentEntity> comments = new ArrayList<>();
    @OneToMany(
//            fetch = FetchType.EAGER,
            mappedBy = "product",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<ProductColorEntity> product_color = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_collection",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "collection_id") })
//    @JsonIgnore
    private List<CollectionEntity> collections = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_promotion",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "promotion_id") })
    @Where(clause = "status=1 and current_date between start_date and end_date")
    private Set<PromotionEntity> promotions = new HashSet<>();
}
