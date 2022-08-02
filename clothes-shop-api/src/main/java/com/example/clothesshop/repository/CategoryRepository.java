package com.example.clothesshop.repository;

import com.example.clothesshop.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {

}
