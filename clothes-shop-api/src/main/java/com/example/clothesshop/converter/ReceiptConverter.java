package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ReceiptDTO;
import com.example.clothesshop.entity.ReceiptEntity;
import org.springframework.stereotype.Component;

@Component
public class ReceiptConverter extends AbstractConverter {

    public ReceiptDTO toDTO(ReceiptEntity entity){
        ReceiptDTO dto = modelMapper.map(entity, ReceiptDTO.class);
        return dto;
    }

    public ReceiptEntity toEntity(ReceiptDTO dto){
        ReceiptEntity entity = modelMapper.map(dto, ReceiptEntity.class);
        return entity;
    }

    public ReceiptEntity toEntity(ReceiptDTO dto, ReceiptEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
