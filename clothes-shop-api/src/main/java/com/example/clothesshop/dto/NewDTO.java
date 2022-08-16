package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class NewDTO extends AbstractDTO<NewDTO>{
    private String title;
    private String content;
    private String thumbnail;
    private String slug;
    private Long user_id;
}
