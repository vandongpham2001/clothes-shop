package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ColorDTO extends AbstractDTO<ColorDTO> {
    private String color;
    @JsonIgnore
    private List<ProductColorDTO> product_color = new ArrayList<>();
}
