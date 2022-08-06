package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CartDTO;
import com.example.clothesshop.entity.CartEntity;
import org.springframework.stereotype.Component;

@Component
public class CartConverter extends AbstractConverter {

    public CartDTO toDTO(CartEntity entity){
        CartDTO dto = modelMapper.map(entity, CartDTO.class);
        return dto;
    }

    public CartEntity toEntity(CartDTO dto){
        CartEntity entity = modelMapper.map(dto, CartEntity.class);
        return entity;
    }

    public CartEntity toEntity(CartDTO dto, CartEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
