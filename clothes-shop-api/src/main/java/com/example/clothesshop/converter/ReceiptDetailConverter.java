package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ReceiptDetailDTO;
import com.example.clothesshop.entity.ReceiptDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class ReceiptDetailConverter extends AbstractConverter {

    public ReceiptDetailDTO toDTO(ReceiptDetailEntity entity){
        ReceiptDetailDTO dto = modelMapper.map(entity, ReceiptDetailDTO.class);
        return dto;
    }

    public ReceiptDetailEntity toEntity(ReceiptDetailDTO dto){
        ReceiptDetailEntity entity = modelMapper.map(dto, ReceiptDetailEntity.class);
        return entity;
    }

    public ReceiptDetailEntity toEntity(ReceiptDetailDTO dto, ReceiptDetailEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
