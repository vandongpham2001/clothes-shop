package com.example.clothesshop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractConverter<DTO, Entity> {
    @Autowired
    private ModelMapper modelMapper;

    public DTO toDTO(Entity entity, Class<DTO> dtoClass){
        DTO dto = modelMapper.map(entity, dtoClass);
        return dto;
    }

    public Entity toEntity(DTO dto, Class<Entity> entityClass){
        Entity entity = modelMapper.map(dto, entityClass);
        return entity;
    }

    public Entity toEntity(DTO dto, Entity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
