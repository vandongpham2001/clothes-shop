package com.example.clothesshop.converter;

import com.example.clothesshop.dto.OrderDetailDTO;
import com.example.clothesshop.dto.request.OrderDetailRequest;
import com.example.clothesshop.entity.OrderDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailConverter extends AbstractConverter {

    public OrderDetailDTO toDTO(OrderDetailEntity entity){
        return modelMapper.map(entity, OrderDetailDTO.class);
    }

    public OrderDetailEntity toEntity(OrderDetailRequest dto){
        return modelMapper.map(dto, OrderDetailEntity.class);
    }

    public OrderDetailEntity toEntity(OrderDetailRequest dto, OrderDetailEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
