package com.example.clothesshop.converter;

import com.example.clothesshop.dto.PromotionDTO;
import com.example.clothesshop.dto.request.PromotionRequest;
import com.example.clothesshop.entity.PromotionEntity;
import org.springframework.stereotype.Component;

@Component
public class PromotionConverter extends AbstractConverter {

    public PromotionDTO toDTO(PromotionEntity entity){
        return modelMapper.map(entity, PromotionDTO.class);
    }

    public PromotionEntity toEntity(PromotionRequest dto){
        return modelMapper.map(dto, PromotionEntity.class);
    }

    public PromotionEntity toEntity(PromotionRequest dto, PromotionEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
