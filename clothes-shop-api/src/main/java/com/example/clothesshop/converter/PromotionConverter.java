package com.example.clothesshop.converter;

import com.example.clothesshop.dto.PromotionDTO;
import com.example.clothesshop.entity.PromotionEntity;
import org.springframework.stereotype.Component;

@Component
public class PromotionConverter extends AbstractConverter {

    public PromotionDTO toDTO(PromotionEntity entity){
        return modelMapper.map(entity, PromotionDTO.class);
    }

    public PromotionEntity toEntity(PromotionDTO dto){
        return modelMapper.map(dto, PromotionEntity.class);
    }

    public PromotionEntity toEntity(PromotionDTO dto, PromotionEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
