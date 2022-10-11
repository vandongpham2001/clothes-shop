package com.example.clothesshop.converter;

import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.dto.request.OrderRequest;
import com.example.clothesshop.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends AbstractConverter {

    public OrderDTO toDTO(OrderEntity entity){
        return modelMapper.map(entity, OrderDTO.class);
    }

    public OrderEntity toEntity(OrderRequest dto){
        return modelMapper.map(dto, OrderEntity.class);
    }

    public OrderEntity toEntity(OrderRequest dto, OrderEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
