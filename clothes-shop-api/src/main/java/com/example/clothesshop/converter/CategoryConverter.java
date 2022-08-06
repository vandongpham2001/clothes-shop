package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    @Autowired
    private ModelMapper modelMapper;

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

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }
}
