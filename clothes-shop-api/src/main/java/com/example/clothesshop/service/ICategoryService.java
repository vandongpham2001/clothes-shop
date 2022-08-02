package com.example.clothesshop.service;

import com.example.clothesshop.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICategoryService {
    //    @Query("SELECT c FROM CategoryEntity c")
//    Page<CategoryDTO> findAll(Pageable pageable);

    List<CategoryDTO> findAll(Pageable pageable);

    List<CategoryDTO> findAll(Sort sort);

    CategoryDTO create(CategoryDTO dto);

    CategoryDTO update(CategoryDTO dto);

    void delete(CategoryDTO dto);
}
