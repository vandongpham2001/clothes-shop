package com.example.clothesshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "News")
@Data
public class NewEntity extends BaseEntity {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String thumbnail;
    private String slug;
    private Integer status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
