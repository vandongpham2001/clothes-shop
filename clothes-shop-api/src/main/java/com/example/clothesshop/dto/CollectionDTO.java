package com.example.clothesshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CollectionDTO extends AbstractDTO<CollectionDTO>{
    private String name;
    private Integer status;
    private String mobile_banner;
    private String pc_banner;
    private List<ProductDTO> products = new ArrayList<>();
}
