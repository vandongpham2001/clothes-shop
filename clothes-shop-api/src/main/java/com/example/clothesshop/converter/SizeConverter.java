package com.example.clothesshop.converter;

import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.entity.SizeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SizeConverter {

    @Autowired
    private ModelMapper modelMapper;

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
