package com.example.clothesshop.service;

import com.example.clothesshop.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {

    List<CategoryDTO> findAll(Pageable pageable);

    List<CategoryDTO> findAll(Sort sort);

    CategoryDTO save(CategoryDTO dto) throws IOException;

    void delete(long[] ids);
}
