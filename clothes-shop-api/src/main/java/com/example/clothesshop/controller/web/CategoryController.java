package com.example.clothesshop.controller.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "categoryApiOfWeb")
@RequestMapping(path = "api/category")
public class CategoryController {

    @GetMapping
    public String findAll(){
        return "Hello world";
    }
}
