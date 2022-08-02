package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.CategoryConverter;
import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import com.example.clothesshop.repository.CategoryRepository;
import com.example.clothesshop.service.ICategoryService;
import com.example.clothesshop.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryDTO> findAll(Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        Page<CategoryEntity> entities = categoryRepository.findAll(pageable);
        for (CategoryEntity item : entities) {
            CategoryDTO dto = categoryConverter.toDTO(item);
            results.add(dto);
        }
        return results;
    }

    public List<CategoryDTO> findAll(Sort sort) {
        List<CategoryDTO> results = new ArrayList<>();
        Iterable<CategoryEntity> entities = categoryRepository.findAll(sort);
        for (CategoryEntity item : entities) {
            CategoryDTO dto = categoryConverter.toDTO(item);
            results.add(dto);
        }
        return results;
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        dto.setSlug(SlugUtil.toSlug(dto.getName()));
        CategoryEntity entity = new CategoryEntity();
        entity = categoryConverter.toEntity(dto);
        entity = categoryRepository.save(entity);
        return categoryConverter.toDTO(entity);
    }

    @Override
    public CategoryDTO update(CategoryDTO dto) {
        return null;
    }

    @Override
    public void delete(CategoryDTO dto) {

    }
}
