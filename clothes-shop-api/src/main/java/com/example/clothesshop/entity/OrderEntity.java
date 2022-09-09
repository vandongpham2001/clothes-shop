package com.example.clothesshop.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
public class OrderEntity extends BaseEntity{
    private Integer status;
    @CreatedDate
    private Date time_order;
    private Date payment_time;
    private Integer payment_method;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    private List<OrderDetailEntity> order_detail = new ArrayList<>();
}
