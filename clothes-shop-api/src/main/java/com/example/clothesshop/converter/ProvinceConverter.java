package com.example.clothesshop.converter;

import com.example.clothesshop.dto.response.ProvinceDetailResponse;
import com.example.clothesshop.dto.response.ProvinceResponse;
import com.example.clothesshop.entity.ProvinceEntity;
import org.springframework.stereotype.Component;

@Component
public class ProvinceConverter extends AbstractConverter {
    public ProvinceResponse toDTO(ProvinceEntity entity){
        return modelMapper.map(entity, ProvinceResponse.class);
    }
    public ProvinceDetailResponse toDetailDTO(ProvinceEntity entity){
        return modelMapper.map(entity, ProvinceDetailResponse.class);
    }
}
