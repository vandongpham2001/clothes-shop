package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends AbstractConverter {

    public CategoryDTO toDTO(CategoryEntity entity){
        CategoryDTO dto = modelMapper.map(entity, CategoryDTO.class);
        return dto;
    }

    public CategoryEntity toEntity(CategoryDTO dto){
        CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
        return entity;
    }

    public CategoryEntity toEntity(CategoryDTO dto, CategoryEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
