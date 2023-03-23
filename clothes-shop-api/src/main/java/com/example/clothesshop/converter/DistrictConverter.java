package com.example.clothesshop.converter;

import com.example.clothesshop.dto.response.DistrictResponse;
import com.example.clothesshop.entity.DistrictEntity;
import org.springframework.stereotype.Component;

@Component
public class DistrictConverter extends AbstractConverter {
    public DistrictResponse toDTO(DistrictEntity entity){
        return modelMapper.map(entity, DistrictResponse.class);
    }
}
