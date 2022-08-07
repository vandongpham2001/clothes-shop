package com.example.clothesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Users")
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity{
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone_number;
    private String address;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    @JsonIgnore
    private List<CommentEntity> comments = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    @JsonIgnore
    private List<CartEntity> carts = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    @JsonIgnore
    private List<OrderEntity> orders = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    @JsonIgnore
    private List<NewEntity> news = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

//    @ManyToMany(fetch = FetchType.EAGER,
//            mappedBy = "users")
//    private Set<RoleEntity> roles = new HashSet<>();

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }
}
