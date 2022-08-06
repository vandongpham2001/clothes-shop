package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CollectionDTO;
import com.example.clothesshop.entity.CollectionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CollectionDTO toDTO(CollectionEntity entity){
        CollectionDTO dto = modelMapper.map(entity, CollectionDTO.class);
        return dto;
    }

    public CollectionEntity toEntity(CollectionDTO dto){
        CollectionEntity entity = modelMapper.map(dto, CollectionEntity.class);
        return entity;
    }

    public CollectionEntity toEntity(CollectionDTO dto, CollectionEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
