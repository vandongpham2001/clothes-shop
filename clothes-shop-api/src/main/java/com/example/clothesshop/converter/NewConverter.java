package com.example.clothesshop.converter;

import com.example.clothesshop.dto.NewDTO;
import com.example.clothesshop.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class NewConverter extends AbstractConverter {

    public NewDTO toDTO(NewEntity entity){
        return modelMapper.map(entity, NewDTO.class);
    }

    public NewEntity toEntity(NewDTO dto){
        return modelMapper.map(dto, NewEntity.class);
    }

    public NewEntity toEntity(NewDTO dto, NewEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
