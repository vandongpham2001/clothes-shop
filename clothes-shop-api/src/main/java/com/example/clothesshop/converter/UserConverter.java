package com.example.clothesshop.converter;

import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter {

    public UserDTO toDTO(UserEntity entity){
        UserDTO dto = modelMapper.map(entity, UserDTO.class);
        return dto;
    }

    public UserEntity toEntity(UserDTO dto){
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        return entity;
    }

    public UserEntity toEntity(UserDTO dto, UserEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
