package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.entity.ColorEntity;
import org.springframework.stereotype.Component;

@Component
public class ColorConverter extends AbstractConverter {

    public ColorDTO toDTO(ColorEntity entity){
        return modelMapper.map(entity, ColorDTO.class);
    }

    public ColorEntity toEntity(ColorDTO dto){
        return modelMapper.map(dto, ColorEntity.class);
    }

    public ColorEntity toEntity(ColorDTO dto, ColorEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
