package com.example.clothesshop.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.example.clothesshop.constant.CloudinaryConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class WebAppContextConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary =  new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CloudinaryConstant.cloud_name,
                "api_key", CloudinaryConstant.api_key,
                "api_secret", CloudinaryConstant.api_secret,
                "secure", true
        ));
        return cloudinary;
    }

//    @Bean
//    public ApiResponse cloudinary() throws Exception {
//        ApiResponse cloudinary = new Cloudinary().api().createUploadPreset(ObjectUtils.asMap(
//                "name", CloudinaryConstant.upload_preset,
//                "unsigned", true,
//                "categorization", "google_tagging,google_video_tagging",
//                "auto_tagging", 0.75,
//                "background_removal", "cloudinary_ai",
//                "folder", CloudinaryConstant.upload_preset));
//        return cloudinary;
//    }

}
