package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CommentDTO;
import com.example.clothesshop.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter extends AbstractConverter {

    public CommentDTO toDTO(CommentEntity entity){
        return modelMapper.map(entity, CommentDTO.class);
    }

    public CommentEntity toEntity(CommentDTO dto){
        return modelMapper.map(dto, CommentEntity.class);
    }

    public CommentEntity toEntity(CommentDTO dto, CommentEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
