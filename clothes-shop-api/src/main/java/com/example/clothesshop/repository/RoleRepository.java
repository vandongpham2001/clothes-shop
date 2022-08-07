package com.example.clothesshop.repository;

import com.example.clothesshop.entity.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
