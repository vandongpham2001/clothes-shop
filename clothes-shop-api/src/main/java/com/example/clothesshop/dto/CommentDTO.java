package com.example.clothesshop.dto;

import lombok.Data;

@Data
public class CommentDTO extends AbstractDTO<CommentDTO>{
    private String content;
    private Float rating;
    private Long parent_id;
    private UserDTO user;
    private ProductDTO product;

}
