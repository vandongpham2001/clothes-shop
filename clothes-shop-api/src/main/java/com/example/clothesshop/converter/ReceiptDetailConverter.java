package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ReceiptDetailDTO;
import com.example.clothesshop.entity.ReceiptDetailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiptDetailConverter {

    @Autowired
    private ModelMapper modelMapper;

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
