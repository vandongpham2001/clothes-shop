package com.example.clothesshop.converter;

import com.example.clothesshop.dto.NewDTO;
import com.example.clothesshop.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class NewConverter extends AbstractConverter {

    public NewDTO toDTO(NewEntity entity){
        NewDTO dto = modelMapper.map(entity, NewDTO.class);
        return dto;
    }

    public NewEntity toEntity(NewDTO dto){
        NewEntity entity = modelMapper.map(dto, NewEntity.class);
        return entity;
    }

    public NewEntity toEntity(NewDTO dto, NewEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
