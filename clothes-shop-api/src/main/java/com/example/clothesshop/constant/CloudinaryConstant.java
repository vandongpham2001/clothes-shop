package com.example.clothesshop.constant;

public class CloudinaryConstant {
    public static final String cloud_name = "vandongpham2001";
    public static final String api_key = "297627859341447";
    public static final String api_secret = "c8w4Tmdzp8HgT5P2JX3Ka5-XUi8";
    public static final String upload_preset = "clothes-shop";
    public static final String resource_type_image = "image";
    public static final String action_upload = "upload";
    public static final String action_destroy = "destroy";
    public static final String CLOUDINARY_URL_UPLOAD = "https://api.cloudinary.com/v1_1/"+cloud_name+"/"+resource_type_image+"/"+action_upload;
    public static final String CLOUDINARY_URL_DESTROY = "https://api.cloudinary.com/v1_1/"+cloud_name+"/"+resource_type_image+"/"+action_destroy;
}
