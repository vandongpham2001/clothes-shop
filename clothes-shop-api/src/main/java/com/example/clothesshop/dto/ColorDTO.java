package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class ColorDTO extends AbstractDTO<ColorDTO> {
    private String color;
    private Integer status;
}
