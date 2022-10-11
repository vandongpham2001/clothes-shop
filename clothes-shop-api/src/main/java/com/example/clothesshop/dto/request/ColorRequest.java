package com.example.clothesshop.dto.request;

import lombok.Data;

@Data
public class ColorRequest extends AbstractRequest<ColorRequest> {
    private String color;
    private Integer status;
}
