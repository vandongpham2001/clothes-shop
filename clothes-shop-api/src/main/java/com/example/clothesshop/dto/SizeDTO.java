package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class SizeDTO extends AbstractDTO<SizeDTO> {
    private String size;
    private Integer status;
}
