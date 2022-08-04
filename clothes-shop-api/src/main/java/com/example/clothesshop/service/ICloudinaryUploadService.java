package com.example.clothesshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ICloudinaryUploadService {
    String uploadFile(MultipartFile file);
    File convertMultiPartToFile(MultipartFile file);
}
