package com.example.clothesshop.repository;

import com.example.clothesshop.entity.SizeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SizeRepository extends PagingAndSortingRepository<SizeEntity, Long> {
}
