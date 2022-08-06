package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.entity.ColorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColorConverter {

    @Autowired
    private ModelMapper modelMapper;

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
