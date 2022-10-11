package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.dto.request.CategoryRequest;
import com.example.clothesshop.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends AbstractConverter {

    public CategoryDTO toDTO(CategoryEntity entity){
        return modelMapper.map(entity, CategoryDTO.class);
    }

    public CategoryEntity toEntity(CategoryRequest dto){
        return modelMapper.map(dto, CategoryEntity.class);
    }

    public CategoryEntity toEntity(CategoryRequest dto, CategoryEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
