package com.example.clothesshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Integer status;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<CommentEntity> comments = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<CartEntity> carts = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
    private List<OrderEntity> orders = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
//    @JsonIgnore
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

    public void removeRole(long role_id) {
        RoleEntity role = this.roles.stream().filter(t -> t.getId() == role_id).findFirst().orElse(null);
        if (role != null) {
            this.roles.remove(role);
            role.getUsers().remove(this);
        }
    }
}
