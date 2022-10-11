package com.example.clothesshop.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class AbstractRequest<T> {
    private Long id;
//    private MultipartFile[] file;
}
