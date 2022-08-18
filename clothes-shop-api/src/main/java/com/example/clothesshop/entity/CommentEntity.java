package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
@Data
public class CommentEntity extends BaseEntity{
    @Column(columnDefinition = "TEXT")
    private String content;
    private Float rating;
    private Long parent_id;
    private Integer status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
