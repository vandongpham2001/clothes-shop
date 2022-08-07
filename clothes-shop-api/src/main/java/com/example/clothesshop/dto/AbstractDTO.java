package com.example.clothesshop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class AbstractDTO<T> {
    private Long id;

    private String createdBy;

    private String modifiedBy;

    private Date createdDate;

    private Date modifiedDate;

    private MultipartFile[] file;

//    private List<T> listResult = new ArrayList<>();
//
//    private Integer page;
//
//    private Integer totalPage;

}
