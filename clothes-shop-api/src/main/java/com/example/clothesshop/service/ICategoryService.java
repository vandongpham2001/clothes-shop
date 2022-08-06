package com.example.clothesshop.service;

import com.example.clothesshop.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {

    Page<CategoryDTO> findAllPageable(Integer status, Pageable pageable);

    List<CategoryDTO> findAll(Integer status, Sort sort);

    CategoryDTO save(CategoryDTO dto) throws IOException;

    void delete(long[] ids);
}
