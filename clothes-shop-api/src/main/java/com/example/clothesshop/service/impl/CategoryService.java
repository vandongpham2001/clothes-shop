package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.CategoryConverter;
import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import com.example.clothesshop.repository.CategoryRepository;
import com.example.clothesshop.service.ICategoryService;
import com.example.clothesshop.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity item : entities){
            CategoryDTO dto = categoryConverter.toDTO(item);
            results.add(dto);
        }
        return results;
    }

    @Override
    public CategoryDTO save(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity = categoryConverter.toEntity(dto);
        System.out.println(SlugUtil.deAccent(entity.getName()));
        entity.setSlug(SlugUtil.toSlug(entity.getName()));
        entity = categoryRepository.save(entity);
        return categoryConverter.toDTO(entity);
    }
}
