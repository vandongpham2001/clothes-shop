package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SizeDTO extends AbstractDTO<SizeDTO> {
    String size;
    @JsonIgnore
    private List<ProductColorSizeDTO> products = new ArrayList<>();
}
