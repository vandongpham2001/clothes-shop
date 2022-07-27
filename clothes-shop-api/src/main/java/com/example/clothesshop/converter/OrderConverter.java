package com.example.clothesshop.converter;

import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    @Autowired
    private ModelMapper modelMapper;

    public OrderDTO toDTO(OrderEntity entity){
        OrderDTO dto = modelMapper.map(entity, OrderDTO.class);
        return dto;
    }

    public OrderEntity toEntity(OrderDTO dto){
        OrderEntity entity = modelMapper.map(dto, OrderEntity.class);
        return entity;
    }

    public OrderEntity toEntity(OrderDTO dto, OrderEntity entity){
        entity = modelMapper.map(dto, OrderEntity.class);
        return entity;
    }
}
