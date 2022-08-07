package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ColorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ColorRepository extends PagingAndSortingRepository<ColorEntity, Long> {
}
