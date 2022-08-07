package com.example.clothesshop.repository;

import com.example.clothesshop.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
