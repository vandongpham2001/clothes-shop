package com.example.clothesshop.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.clothesshop.constant.CloudinaryConstant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public class CloudinaryUtil {
    public static String upload(Cloudinary cloudinary, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Map r = cloudinary.uploader().unsignedUpload(bytes, CloudinaryConstant.upload_preset,ObjectUtils.asMap("resource_type", "auto"));
        String img = (String) r.get("secure_url");
        return img;
//        ApiResponse api = new Cloudinary().api().uploadPreset(String.valueOf(bytes), ObjectUtils.asMap(
//                "cloud_name", CloudinaryConstant.cloud_name,
//                "api_key", CloudinaryConstant.api_key,
//                "name", CloudinaryConstant.upload_preset,
//                "unsigned", true,
//                "categorization", "google_tagging,google_video_tagging",
//                "auto_tagging", 0.75,
//                "background_removal", "cloudinary_ai",
//                "folder", CloudinaryConstant.upload_preset));
////        Map r = new Cloudinary().uploader().upload(bytes, ObjectUtils.asMap(
////                "upload_preset", CloudinaryConstant.upload_preset));
//        String img = (String) api.get("secure_url");
//        return img;
    }

    public static String destroy(Cloudinary cloudinary, String file_name) throws IOException {
        Map r = cloudinary.uploader().destroy(file_name,
                ObjectUtils.emptyMap());
        String result = (String) r.get("result");
        return result;
    }

    public static String getNameImgFromUrlCloudinary(String url) {
        String[] format_img = {".jpg", ".png"};
        String preset = CloudinaryConstant.upload_preset;
        int start_pos = url.indexOf(preset);
        if (start_pos<0)
            return null;
        String img_name = url.substring(start_pos);
        for (String item : format_img){
            if (img_name.contains(item)){
                String new_img_name = img_name.replace(item, "");
                return new_img_name;
            }
        }
        return null;
    }
}
