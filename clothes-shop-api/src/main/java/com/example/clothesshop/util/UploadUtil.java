package com.example.clothesshop.util;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.example.clothesshop.constant.CloudinaryConstant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public class UploadUtil {
    public static String uploadCloudinary(Cloudinary cloudinary, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Map r = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "auto"));
        String img = (String) r.get("secure_url");
        return img;
//        ApiResponse apiResponse = new Cloudinary().api().createUploadPreset(ObjectUtils.asMap(
//                "name", CloudinaryConstant.upload_preset,
//                "unsigned", true,
//                "categorization", "google_tagging,google_video_tagging",
//                "auto_tagging", 0.75,
//                "background_removal", "cloudinary_ai",
//                "folder", CloudinaryConstant.upload_preset));
//        Cloudinary cloudinary = new Cloudinary();
//        Map r = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("upload_preset", CloudinaryConstant.upload_preset));
//        String img = (String) r.get("secure_url");
//        return img;
    }

    public static String getNameImgFromUrlCloudinary(String url, String preset) {
        int start_pos = url.indexOf(preset);
        String img_name = url.substring(start_pos);
        return img_name;
    }
}
