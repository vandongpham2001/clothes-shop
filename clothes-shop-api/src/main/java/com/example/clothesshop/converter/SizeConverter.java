package com.example.clothesshop.converter;

import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.dto.request.SizeRequest;
import com.example.clothesshop.entity.SizeEntity;
import org.springframework.stereotype.Component;

@Component
public class SizeConverter extends AbstractConverter {

    public SizeDTO toDTO(SizeEntity entity){
        return modelMapper.map(entity, SizeDTO.class);
    }

    public SizeEntity toEntity(SizeRequest dto){
        return modelMapper.map(dto, SizeEntity.class);
    }

    public SizeEntity toEntity(SizeRequest dto, SizeEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
