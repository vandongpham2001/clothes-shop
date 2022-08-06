package com.example.clothesshop.converter;

import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends AbstractConverter {

    public OrderDTO toDTO(OrderEntity entity){
        OrderDTO dto = modelMapper.map(entity, OrderDTO.class);
        return dto;
    }

    public OrderEntity toEntity(OrderDTO dto){
        OrderEntity entity = modelMapper.map(dto, OrderEntity.class);
        return entity;
    }

    public OrderEntity toEntity(OrderDTO dto, OrderEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
