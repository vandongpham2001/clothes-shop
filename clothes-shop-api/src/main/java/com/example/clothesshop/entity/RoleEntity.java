package com.example.clothesshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
    private Integer status;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "roles")
//    @JsonIgnore
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
