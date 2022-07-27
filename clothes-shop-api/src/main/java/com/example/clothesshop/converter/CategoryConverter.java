package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO toDTO(CategoryEntity entity){
//        CategoryDTO dto = new CategoryDTO();
//        if (entity.getId()!=null){
//            dto.setId(entity.getId());
//        }
//        dto.setCreatedDate(entity.getCreatedDate());
//        dto.setCreatedBy(entity.getCreatedBy());
//        dto.setModifiedDate(entity.getModifiedDate());
//        dto.setModifiedBy(entity.getModifiedBy());
//        dto.setName(entity.getName());
//        dto.setDescription(entity.getDescription());
//        dto.setImage(entity.getImage());
//        dto.setSlug(entity.getSlug());
//        dto.setStatus(entity.getStatus());
//        dto.setParent_id(entity.getParent_id());
        CategoryDTO dto = modelMapper.map(entity, CategoryDTO.class);
        return dto;
    }

    public CategoryEntity toEntity(CategoryDTO dto){
//        CategoryEntity entity = new CategoryEntity();
//        entity.setName(dto.getName());
//        entity.setDescription(dto.getDescription());
//        entity.setImage(dto.getImage());
//        entity.setSlug(dto.getSlug());
//        entity.setStatus(dto.getStatus());
//        entity.setParent_id(dto.getParent_id());
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
