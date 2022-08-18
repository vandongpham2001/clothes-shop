package com.example.clothesshop;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.service.impl.RoleService;
import com.example.clothesshop.service.impl.UserService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

//exclude = {SecurityAutoConfiguration.class}
@SpringBootApplication()
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ClothesShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothesShopApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		return modelMapper;
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService, RoleService roleService){
//		return args -> {
//			roleService.save(new RoleDTO("ROLE_USER", "", new ArrayList<>()));
//			roleService.save(new RoleDTO("ROLE_ADMIN", "", new ArrayList<>()));
//
//			userService.save(new UserDTO("Dong", "dong","dong@gmail.com", "1234", "0123456789", "Quang Nam", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//			userService.save(new UserDTO("Anh", "anh","anh@gmail.com", "1234", "0123456789", "Quang Nam", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//			userService.save(new UserDTO("Tuan", "tuan","tuan@gmail.com", "1234", "0123456789", "Quang Nam", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//			userService.save(new UserDTO("Pham", "pham","pham@gmail.com","1234", "0123456789", "Quang Nam", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//
//			userService.addRoleToUser("dong", SystemConstant.ADMIN_ROLE);
//			userService.addRoleToUser("anh", SystemConstant.ADMIN_ROLE);
//			userService.addRoleToUser("tuan", SystemConstant.ADMIN_ROLE);
//			userService.addRoleToUser("pham", SystemConstant.USER_ROLE);
//		};
//	}
}
