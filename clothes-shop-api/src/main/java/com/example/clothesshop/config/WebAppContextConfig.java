package com.example.clothesshop.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.clothesshop.constant.CloudinaryConstant;
import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.service.impl.RoleService;
import com.example.clothesshop.service.impl.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class WebAppContextConfig {

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        return resolver;
//    }

    @Bean
    public PutAwareCommonsMultipartResolver multipartResolver() {
        PutAwareCommonsMultipartResolver resolver = new PutAwareCommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");

        return resolver;
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
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
//    @Bean
//    CommandLineRunner run(UserService userService, RoleService roleService){
//		return args -> {
//			roleService.save(new RoleDTO("ROLE_ADMIN", "", SystemConstant.ACTIVE_STATUS, new ArrayList<>()));
//			roleService.save(new RoleDTO("ROLE_USER", "", SystemConstant.ACTIVE_STATUS, new ArrayList<>()));
//
//			userService.save(new UserDTO("Admin", "admin","admin@gmail.com","1234", "0123456789", "Quang Nam", 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//			userService.save(new UserDTO("Dong", "dong","dong@gmail.com", "1234", "0123456789", "Quang Nam", 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//			userService.save(new UserDTO("Anh", "anh","anh@gmail.com", "1234", "0123456789", "Quang Nam", 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//			userService.save(new UserDTO("Tuan", "tuan","tuan@gmail.com", "1234", "0123456789", "Quang Nam", 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//			userService.save(new UserDTO("Pham", "pham","pham@gmail.com","1234", "0123456789", "Quang Nam", 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//
//			userService.addRoleToUser("admin", SystemConstant.ADMIN_ROLE);
//			userService.addRoleToUser("dong", SystemConstant.ADMIN_ROLE);
//			userService.addRoleToUser("dong", SystemConstant.USER_ROLE);
//			userService.addRoleToUser("anh", SystemConstant.USER_ROLE);
//			userService.addRoleToUser("tuan", SystemConstant.USER_ROLE);
//			userService.addRoleToUser("pham", SystemConstant.USER_ROLE);
//		};
//	}
}
