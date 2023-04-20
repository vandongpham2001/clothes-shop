package com.example.clothesshop.converter;

import com.example.clothesshop.dto.response.WardDetailResponse;
import com.example.clothesshop.dto.response.WardResponse;
import com.example.clothesshop.entity.WardEntity;
import org.springframework.stereotype.Component;

@Component
public class WardConverter extends AbstractConverter{
    public WardResponse toDTO(WardEntity entity){
        return modelMapper.map(entity, WardResponse.class);
    }
    public WardDetailResponse toDetailDTO(WardEntity entity){
        return modelMapper.map(entity, WardDetailResponse.class);
    }
}
