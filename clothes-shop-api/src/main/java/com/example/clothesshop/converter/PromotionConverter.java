package com.example.clothesshop.converter;

import com.example.clothesshop.dto.PromotionDTO;
import com.example.clothesshop.entity.PromotionEntity;
import org.springframework.stereotype.Component;

@Component
public class PromotionConverter extends AbstractConverter {

    public PromotionDTO toDTO(PromotionEntity entity){
        PromotionDTO dto = modelMapper.map(entity, PromotionDTO.class);
        return dto;
    }

    public PromotionEntity toEntity(PromotionDTO dto){
        PromotionEntity entity = modelMapper.map(dto, PromotionEntity.class);
        return entity;
    }

    public PromotionEntity toEntity(PromotionDTO dto, PromotionEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
