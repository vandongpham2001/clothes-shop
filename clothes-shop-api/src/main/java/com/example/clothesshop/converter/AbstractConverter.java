package com.example.clothesshop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public abstract class AbstractConverter {
    @Autowired
    protected ModelMapper modelMapper;

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }

//    public DTO toDTO(Entity entity, Class<DTO> dtoClass){
//        DTO dto = modelMapper.map(entity, dtoClass);
//        return dto;
//    }
//
//    public Entity toEntity(DTO dto, Class<Entity> entityClass){
//        Entity entity = modelMapper.map(dto, entityClass);
//        return entity;
//    }
//
//    public Entity toEntity(DTO dto, Entity entity){
//        modelMapper.map(dto, entity);
//        return entity;
//    }
}
