package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        entity = modelMapper.map(dto, CategoryEntity.class);
//        entity.setName(dto.getName());
//        entity.setDescription(dto.getDescription());
//        entity.setImage(dto.getImage());
//        entity.setSlug(dto.getSlug());
//        entity.setStatus(dto.getStatus());
//        entity.setParent_id(dto.getParent_id());
        return entity;
    }
}
