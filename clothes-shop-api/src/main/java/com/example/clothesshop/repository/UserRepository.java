package com.example.clothesshop.repository;

import com.example.clothesshop.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    Page<UserEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<UserEntity> findByStatus(Integer status, Sort sort);
}
