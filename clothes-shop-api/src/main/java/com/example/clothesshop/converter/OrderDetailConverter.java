package com.example.clothesshop.converter;

import com.example.clothesshop.dto.OrderDetailDTO;
import com.example.clothesshop.entity.OrderDetailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailConverter {

    @Autowired
    private ModelMapper modelMapper;

    public OrderDetailDTO toDTO(OrderDetailEntity entity){
        OrderDetailDTO dto = modelMapper.map(entity, OrderDetailDTO.class);
        return dto;
    }

    public OrderDetailEntity toEntity(OrderDetailDTO dto){
        OrderDetailEntity entity = modelMapper.map(dto, OrderDetailEntity.class);
        return entity;
    }

    public OrderDetailEntity toEntity(OrderDetailDTO dto, OrderDetailEntity entity){
        entity = modelMapper.map(dto, OrderDetailEntity.class);
        return entity;
    }
}
