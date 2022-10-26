package com.example.clothesshop.controller.web;

import com.example.clothesshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController(value = "productApiOfWeb")
@RequestMapping(path = "api/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/max-price")
    public BigDecimal getMaxPriceProduct(){
        return productService.findMaxPriceProduct();
    }

//    AllProduct

//    ProductByCategory

//    ProductByCollection

//    ProductByPromotion

//    Product Filter

//    Product Detail

//    New Product

//    Product Weekly Best

//    Product Search

//    Menu
}
