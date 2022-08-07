package com.example.clothesshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Roles")
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity {
    private String name;
    private String role;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "role_user",
//            joinColumns = {@JoinColumn(name = "role_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")})
//    private Set<UserEntity> users = new HashSet<>();
//
//    public void addUser(UserEntity user) {
//        this.users.add(user);
//    }
}
