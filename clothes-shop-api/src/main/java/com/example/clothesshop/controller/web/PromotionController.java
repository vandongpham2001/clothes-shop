package com.example.clothesshop.controller.web;

import com.example.clothesshop.dto.PromotionDTO;
import com.example.clothesshop.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "apiPromotionOfWeb")
@RequestMapping(path = "api/promotion")
public class PromotionController {
    @Autowired
    IPromotionService promotionService;
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllPromotion(){
        Map<String, Object> response = new HashMap<>();
        List<PromotionDTO> listPromotion = promotionService.findCurrentPromotion();
        response.put("promotions", listPromotion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public PromotionDTO getPromotionById(@PathVariable("id") long id) {
        return promotionService.findById(id);
    }
}
