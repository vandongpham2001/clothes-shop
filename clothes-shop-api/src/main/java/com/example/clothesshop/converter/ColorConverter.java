package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.entity.ColorEntity;
import org.springframework.stereotype.Component;

@Component
public class ColorConverter extends AbstractConverter {

    public ColorDTO toDTO(ColorEntity entity){
        ColorDTO dto = modelMapper.map(entity, ColorDTO.class);
        return dto;
    }

    public ColorEntity toEntity(ColorDTO dto){
        ColorEntity entity = modelMapper.map(dto, ColorEntity.class);
        return entity;
    }

    public ColorEntity toEntity(ColorDTO dto, ColorEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
