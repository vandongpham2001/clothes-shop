package com.example.clothesshop.converter;

import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter {

    public UserDTO toDTO(UserEntity entity){
        return modelMapper.map(entity, UserDTO.class);
    }

    public UserEntity toEntity(UserDTO dto){
        return modelMapper.map(dto, UserEntity.class);
    }

    public UserEntity toEntity(UserDTO dto, UserEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
