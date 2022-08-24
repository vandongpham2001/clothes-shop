package com.example.clothesshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//exclude = {SecurityAutoConfiguration.class}
@SpringBootApplication()
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ClothesShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothesShopApplication.class, args);
    }
}
