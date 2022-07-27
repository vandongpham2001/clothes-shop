package com.example.clothesshop.converter;

import com.example.clothesshop.dto.CommentDTO;
import com.example.clothesshop.entity.CommentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CommentDTO toDTO(CommentEntity entity){
        CommentDTO dto = modelMapper.map(entity, CommentDTO.class);
        return dto;
    }

    public CommentEntity toEntity(CommentDTO dto){
        CommentEntity entity = modelMapper.map(dto, CommentEntity.class);
        return entity;
    }

    public CommentEntity toEntity(CommentDTO dto, CommentEntity entity){
        entity = modelMapper.map(dto, CommentEntity.class);
        return entity;
    }
}
