package com.example.clothesshop.converter;

import com.example.clothesshop.dto.OrderDetailDTO;
import com.example.clothesshop.entity.OrderDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailConverter extends AbstractConverter {

    public OrderDetailDTO toDTO(OrderDetailEntity entity){
        OrderDetailDTO dto = modelMapper.map(entity, OrderDetailDTO.class);
        return dto;
    }

    public OrderDetailEntity toEntity(OrderDetailDTO dto){
        OrderDetailEntity entity = modelMapper.map(dto, OrderDetailEntity.class);
        return entity;
    }

    public OrderDetailEntity toEntity(OrderDetailDTO dto, OrderDetailEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
