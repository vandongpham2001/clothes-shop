package com.example.clothesshop.service;

import com.example.clothesshop.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO save(CategoryDTO dto);
}
