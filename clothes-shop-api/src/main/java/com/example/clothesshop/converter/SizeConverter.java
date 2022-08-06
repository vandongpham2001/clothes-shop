package com.example.clothesshop.converter;

import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.entity.SizeEntity;
import org.springframework.stereotype.Component;

@Component
public class SizeConverter extends AbstractConverter {

    public SizeDTO toDTO(SizeEntity entity){
        SizeDTO dto = modelMapper.map(entity, SizeDTO.class);
        return dto;
    }

    public SizeEntity toEntity(SizeDTO dto){
        SizeEntity entity = modelMapper.map(dto, SizeEntity.class);
        return entity;
    }

    public SizeEntity toEntity(SizeDTO dto, SizeEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
