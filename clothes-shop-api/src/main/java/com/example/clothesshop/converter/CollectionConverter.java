package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CollectionDTO;
import com.example.clothesshop.entity.CollectionEntity;
import org.springframework.stereotype.Component;

@Component
public class CollectionConverter extends AbstractConverter {

    public CollectionDTO toDTO(CollectionEntity entity){
        return modelMapper.map(entity, CollectionDTO.class);
    }

    public CollectionEntity toEntity(CollectionDTO dto){
        return modelMapper.map(dto, CollectionEntity.class);
    }

    public CollectionEntity toEntity(CollectionDTO dto, CollectionEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
